package team;

import com.team19.Main;
import com.team19.entity.Employee;
import com.team19.entity.Team;
import com.team19.repository.EmployeeRepository;
import com.team19.repository.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Main.class, config.H2TestProfileConfig.class })
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TeamIntegrationTest {
    @Autowired
    private TeamRepository repository;

    @Autowired
    private EmployeeRepository employeeMockDB;

    @Autowired
    private MockMvc mvc;

    private List<Team> dataset1 = Arrays.asList(
        new Team.Builder(0)
            .withTeamName("Team Green")
            .withTeamManagerId(1)
            .build(),

        new Team.Builder(1)
            .withTeamName("Team Red")
            .withTeamManagerId(0)
            .build()
    );

    private List<Employee> employees = Arrays.asList(
        new Employee.Builder(0)
            .withTeamId(1)
            .build(),

        new Employee.Builder(1)
            .withTeamId(0)
            .build()
    );

    @Before
    public void setUp() {
        // Save and remove according to the functional dependencies
        repository.deleteAll();
        employeeMockDB.deleteAll();
        employeeMockDB.saveAll(employees);

        repository.saveAll(dataset1);
    }

    @Test
    public void testGetAllTeams() throws Exception {
        mvc.perform(get("/teams"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[0].teamName").value("Team Green"))
            .andExpect(jsonPath("$.[0].teamManagerId").value(1))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].teamId").value(1))
            .andExpect(jsonPath("$.[1].teamName").value("Team Red"))
            .andExpect(jsonPath("$.[1].teamManagerId").value(0))
            .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    public void testGetOneTeam() throws Exception {
        mvc.perform(get("/teams/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.teamId").value(1))
            .andExpect(jsonPath("$.teamName").value("Team Red"))
            .andExpect(jsonPath("$.teamManagerId").value(0));
    }

    @Test
    public void testGetTeamWithInvalidTeamId() throws Exception {
        mvc.perform(get("/teams/10"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    public void testPostTeam() throws Exception {
        Employee employee = new Employee.Builder(2)
            .withTeamId(3)
            .withFirstName("Joshua")
            .build();
        employeeMockDB.save(employee);

        Team team = new Team.Builder(3)
            .withTeamName("Team Blue")
            .withTeamManagerId(2)
            .build();

        mvc.perform(post("/teams")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamId\": 3, \"teamName\": \"Team Blue\", \"teamManagerId\": 2 }"))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.teamId").value(3))
            .andExpect(jsonPath("$.teamName").value("Team Blue"))
            .andExpect(jsonPath("$.teamManagerId").value(2))
            .andExpect(jsonPath("$.teamManager.firstName").value("Joshua"));

        Assert.assertTrue(repository.findById(3).isPresent());

        Team res = repository.findById(3).orElse(null);
        Assert.assertNotNull(res);
        Assert.assertEquals(team, res);
    }

    @Test
    public void testPostInvalidTeam() throws Exception {
        mvc.perform(post("/teams")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamId\": 3, \"teamName\": \"\", \"teamManagerId\": 2 }"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutTeam() throws Exception {
        Team team = new Team.Builder(1)
            .withTeamName("NewTeam1")
            .withTeamManagerId(0)
            .build();

        mvc.perform(put("/teams/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamName\": \"NewTeam1\" }"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.teamId").value(1))
            .andExpect(jsonPath("$.teamName").value("NewTeam1"))
            .andExpect(jsonPath("$.teamManagerId").value(0));

        Team res = repository.findById(1).orElse(null);
        Assert.assertNotNull(res);
        Assert.assertEquals(team, res);
    }

    @Test
    public void testPutTeamWithInvalidTeamId() throws Exception {
        mvc.perform(put("/teams/5")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamName\": \"NewTeam1\" }"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutTeamWithTeamNameContainingTrailingSpaces() throws Exception {
        Team team = new Team.Builder(1)
            .withTeamName("NewTeam1")
            .withTeamManagerId(0)
            .build();

        mvc.perform(put("/teams/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamName\": \"   NewTeam1   \" }"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.teamId").value(1))
            .andExpect(jsonPath("$.teamName").value("NewTeam1"))
            .andExpect(jsonPath("$.teamManagerId").value(0));

        Team res = repository.findById(1).orElse(null);
        Assert.assertEquals(team, res);
    }

    @Test
    public void testPutTeamWithEmptyTeamName() throws Exception {
        mvc.perform(put("/teams/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"teamName\": \"  \" }"))
            .andDo(print())
            .andExpect(status().isBadRequest());

        Team team = repository.findById(1).orElse(null);
        Assert.assertEquals(dataset1.get(1), team);
    }

    @Test
    public void testDeleteTeam() throws Exception {
        mvc.perform(delete("/teams/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.teamId").value(dataset1.get(1).getTeamId()))
            .andExpect(jsonPath("$.teamName").value(dataset1.get(1).getTeamName()))
            .andExpect(jsonPath("$.teamManagerId").value(dataset1.get(1).getTeamManagerId()));

        Team team = repository.findById(1).orElse(null);
        Assert.assertNull(team);
    }

    @Test
    public void testDeleteInvalidTeam() throws Exception {
        mvc.perform(delete("/teams/10"))
            .andDo(print())
            .andExpect(status().isBadRequest());

        Team team = repository.findById(10).orElse(null);
        Assert.assertNull(team);
    }

}
