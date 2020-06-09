package employee;

import com.team19.Main;
import com.team19.entity.Employee;
import com.team19.repository.EmployeeRepository;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

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
public class EmployeeIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private MockMvc mvc;

    private List<Employee> dataset1 = Arrays.asList(
        new Employee.Builder(0)
            .withFirstName("Jack")
            .withLastName("Torrance")
            .withPosition("Scrum Master")
            .withEmail("jackt@email.com")
            .withTeamId(0)
            .build(),

        new Employee.Builder(1)
            .withFirstName("Wendy")
            .withLastName("Torrance")
            .withPosition("Admin")
            .withEmail("wendy@email.com")
            .withTeamId(0)
            .build(),

        new Employee.Builder(2)
            .withFirstName("Jack")
            .withLastName("Sparrow")
            .withPosition("Associate")
            .withEmail("jacks@email.com")
            .withTeamId(1)
            .build(),

        new Employee.Builder(3)
            .withFirstName("Michael")
            .withLastName("Corleone")
            .withPosition("Scrum Master")
            .withEmail("michael@email.com")
            .withTeamId(1)
            .build(),

        new Employee.Builder(4)
            .withFirstName("Vito")
            .withLastName("Corleone")
            .withPosition("Associate")
            .withEmail("vito@email.com")
            .withTeamId(1)
            .build()
    );

    private void useDataSet1() {
        repository.saveAll(dataset1);
    }

    /**
     * Reset the mock database after every test.
     */
    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(0))
            .andExpect(jsonPath("$.[0].firstName").value("Jack"))
            .andExpect(jsonPath("$.[0].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("jackt@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].eid").value(1))
            .andExpect(jsonPath("$.[1].firstName").value("Wendy"))
            .andExpect(jsonPath("$.[1].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[1].position").value("Admin"))
            .andExpect(jsonPath("$.[1].email").value("wendy@email.com"))
            .andExpect(jsonPath("$.[1].teamId").value(0))
            .andExpect(jsonPath("$.[2]").exists())
            .andExpect(jsonPath("$.[2].eid").value(2))
            .andExpect(jsonPath("$.[2].firstName").value("Jack"))
            .andExpect(jsonPath("$.[2].lastName").value("Sparrow"))
            .andExpect(jsonPath("$.[2].position").value("Associate"))
            .andExpect(jsonPath("$.[2].email").value("jacks@email.com"))
            .andExpect(jsonPath("$.[2].teamId").value(1))
            .andExpect(jsonPath("$.[3]").exists())
            .andExpect(jsonPath("$.[3].eid").value(3))
            .andExpect(jsonPath("$.[3].firstName").value("Michael"))
            .andExpect(jsonPath("$.[3].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[3].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[3].email").value("michael@email.com"))
            .andExpect(jsonPath("$.[3].teamId").value(1))
            .andExpect(jsonPath("$.[4]").exists())
            .andExpect(jsonPath("$.[4].eid").value(4))
            .andExpect(jsonPath("$.[4].firstName").value("Vito"))
            .andExpect(jsonPath("$.[4].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[4].position").value("Associate"))
            .andExpect(jsonPath("$.[4].email").value("vito@email.com"))
            .andExpect(jsonPath("$.[4].teamId").value(1))
            .andExpect(jsonPath("$.[5]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByEid() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?eid=3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(3))
            .andExpect(jsonPath("$.[0].firstName").value("Michael"))
            .andExpect(jsonPath("$.[0].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("michael@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(1))
            .andExpect(jsonPath("$.[1]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByFirstName() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?firstName=Jack"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(0))
            .andExpect(jsonPath("$.[0].firstName").value("Jack"))
            .andExpect(jsonPath("$.[0].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("jackt@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].eid").value(2))
            .andExpect(jsonPath("$.[1].firstName").value("Jack"))
            .andExpect(jsonPath("$.[1].lastName").value("Sparrow"))
            .andExpect(jsonPath("$.[1].position").value("Associate"))
            .andExpect(jsonPath("$.[1].email").value("jacks@email.com"))
            .andExpect(jsonPath("$.[1].teamId").value(1))
            .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByLastName() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?lastName=Corleone"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(3))
            .andExpect(jsonPath("$.[0].firstName").value("Michael"))
            .andExpect(jsonPath("$.[0].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("michael@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(1))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].eid").value(4))
            .andExpect(jsonPath("$.[1].firstName").value("Vito"))
            .andExpect(jsonPath("$.[1].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[1].position").value("Associate"))
            .andExpect(jsonPath("$.[1].email").value("vito@email.com"))
            .andExpect(jsonPath("$.[1].teamId").value(1))
            .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByPosition() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?position=Scrum Master"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(0))
            .andExpect(jsonPath("$.[0].firstName").value("Jack"))
            .andExpect(jsonPath("$.[0].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("jackt@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].eid").value(3))
            .andExpect(jsonPath("$.[1].firstName").value("Michael"))
            .andExpect(jsonPath("$.[1].lastName").value("Corleone"))
            .andExpect(jsonPath("$.[1].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[1].email").value("michael@email.com"))
            .andExpect(jsonPath("$.[1].teamId").value(1))
            .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByEmail() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?email=wendy@email.com"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(1))
            .andExpect(jsonPath("$.[0].firstName").value("Wendy"))
            .andExpect(jsonPath("$.[0].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[0].position").value("Admin"))
            .andExpect(jsonPath("$.[0].email").value("wendy@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[1]").doesNotExist());
    }

    @Test
    public void testGetEmployeesByTeamId() throws Exception {
        useDataSet1();

        mvc.perform(get("/employees?teamId=0"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].eid").value(0))
            .andExpect(jsonPath("$.[0].firstName").value("Jack"))
            .andExpect(jsonPath("$.[0].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[0].position").value("Scrum Master"))
            .andExpect(jsonPath("$.[0].email").value("jackt@email.com"))
            .andExpect(jsonPath("$.[0].teamId").value(0))
            .andExpect(jsonPath("$.[1]").exists())
            .andExpect(jsonPath("$.[1].eid").value(1))
            .andExpect(jsonPath("$.[1].firstName").value("Wendy"))
            .andExpect(jsonPath("$.[1].lastName").value("Torrance"))
            .andExpect(jsonPath("$.[1].position").value("Admin"))
            .andExpect(jsonPath("$.[1].email").value("wendy@email.com"))
            .andExpect(jsonPath("$.[1].teamId").value(0))
            .andExpect(jsonPath("$.[2].teamId").doesNotExist());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        useDataSet1();

        int eid = 3;
        mvc.perform(get("/employees/" + eid))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.eid").value(dataset1.get(3).getEid()))
            .andExpect(jsonPath("$.firstName").value(dataset1.get(3).getFirstName()))
            .andExpect(jsonPath("$.lastName").value(dataset1.get(3).getLastName()))
            .andExpect(jsonPath("$.position").value(dataset1.get(3).getPosition()))
            .andExpect(jsonPath("$.email").value(dataset1.get(3).getEmail()))
            .andExpect(jsonPath("$.teamId").value(dataset1.get(3).getTeamId()));
    }

    @Test
    public void testPostEmployee() throws Exception {
        // Create sample record
        Employee employee = new Employee.Builder(5)
            .withFirstName("Jules")
            .withLastName("Winnfield")
            .withEmail("jules@email.com")
            .withPosition("Admin")
            .withTeamId(2)
            .build();

        // Build POST request
        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        // Expecting HTTP "Is Created" status
        mvc.perform(request).andExpect(status().isCreated());

        // Expects to find record in repository
        Employee result = repository.findById(employee.getEid()).orElse(null);

        Assert.assertNotNull(result);
        Assert.assertEquals(employee, result);
    }

    @Test
    public void testPostInvalidEmployeeWithExistingEid() throws Exception {
        useDataSet1();

        Employee employee = new Employee.Builder(0)
            .withFirstName("Barry")
            .withLastName("Lyndon")
            .withPosition("Associate")
            .withEmail("barry@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertEquals(result, dataset1.get(employee.getEid()));
    }

    @Test
    public void testPostInvalidEmployeeWithEmptyStringAsFirstName() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("")
            .withLastName("Torrance")
            .withPosition("Associate")
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithNullAsFirstName() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName(null)
            .withLastName("Torrance")
            .withPosition("Associate")
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithEmptyStringAsLastName() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("")
            .withPosition("Associate")
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithNullAsLastName() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName(null)
            .withPosition("Associate")
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithEmptyStringAsPosition() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("Torrance")
            .withPosition("")
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithNullAsPosition() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("Torrance")
            .withPosition(null)
            .withEmail("danny@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithEmptyStringAsEmail() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("Torrance")
            .withPosition("Associate")
            .withEmail("")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithNullAsEmail() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("Torrance")
            .withPosition("Associate")
            .withEmail(null)
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostInvalidEmployeeWithNullAsTeamId() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName("Danny")
            .withLastName("Torrance")
            .withPosition("Associate")
            .withEmail("danny@email.com")
            .withTeamId(null)
            .build();

        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNull(result);
    }

    @Test
    public void testPostValidEmployeeWithExtraSpacesAndTabs() throws Exception {
        Employee employee = new Employee.Builder(0)
            .withFirstName(" Alex ")
            .withLastName(" DeLarge ")
            .withPosition("  Scrum Master  ")
            .withEmail("alex@email.com")
            .withTeamId(1)
            .build();

        System.out.println(employee.toJson());
        MockHttpServletRequestBuilder request = post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(employee.toJson());

        mvc.perform(request).andExpect(status().isCreated());

        Employee expected = new Employee.Builder(0)
            .withFirstName("Alex")
            .withLastName("DeLarge")
            .withPosition("Scrum Master")
            .withEmail("alex@email.com")
            .withTeamId(1)
            .build();

        Employee result = repository.findById(employee.getEid()).orElse(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testPutEmployee() throws Exception {
        useDataSet1();

        // Target ID of patch
        int patchId = 0;

        // Create new content of employee
        Employee expected = new Employee.Builder(patchId)
            .withFirstName("Lorem")
            .withLastName("Ipsum")
            .withEmail("lorem@email.com")
            .withPosition("Admin")
            .withTeamId(2)
            .build();

        // Build HTTP PUT Request
        MockHttpServletRequestBuilder request = put("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(expected.toJson());

        mvc.perform(request).andExpect(status().isNoContent());

        Employee actual = repository.findById(0).orElse(null);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPutEmployeeWithNewId() throws Exception {
        useDataSet1();

        // Arbitrary ID that isn't within the range [0..5]
        int patchId = 237;

        Employee expected = new Employee.Builder(patchId)
            .withFirstName("Lorem")
            .withLastName("Ipsum")
            .withPosition("Scrum Master")
            .withEmail("lorem@email.com")
            .withTeamId(1)
            .build();

        MockHttpServletRequestBuilder request = put("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(expected.toJson());

        // Expecting HTTP Status: isCreated
        mvc.perform(request).andExpect(status().isCreated());

        Employee actual = repository.findById(patchId).orElse(null);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPutEmployeeWithFieldsEqualNull() throws Exception {
        useDataSet1();

        // Create new content of employee
        Employee expected = new Employee.Builder(null).build();

        // Build HTTP PUT Request
        MockHttpServletRequestBuilder request = put("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(expected.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee actual = repository.findById(0).orElse(null);
        Assert.assertNotNull(actual);
        Assert.assertEquals(dataset1.get(0), actual);
    }

    @Test
    public void testPutEmployeeWithInvalidPatchIdAsParam() throws Exception {
        useDataSet1();

        // Target Patch ID (Invalid)
        int patchId = -1;

        // Create new content of employee
        Employee expected = new Employee.Builder(patchId)
            .withFirstName("Barry")
            .withLastName("Lyndon")
            .withPosition("Admin")
            .withEmail("barry@email.com")
            .withTeamId(0)
            .build();

        // Build HTTP PUT Request
        MockHttpServletRequestBuilder request = put("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(expected.toJson());

        mvc.perform(request).andExpect(status().isBadRequest());

        Employee actual = repository.findById(patchId).orElse(null);
        Assert.assertNull(actual);

        // Original record isn't modified
        actual = repository.findById(1).orElse(null);
        Assert.assertNotNull(actual);
        Assert.assertEquals(dataset1.get(1), actual);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        useDataSet1();
        int eid = 0;
        mvc.perform(delete("/employees/" + eid)).andExpect(status().isNoContent());
        Employee actual = repository.findById(eid).orElse(null);
        Assert.assertNull(actual);
    }

    @Test
    public void testDeleteEmployeeWithInvalidId() throws Exception {
        useDataSet1();
        // Arbitrary ID that's not in [0..5]
        int eid = 237;
        mvc.perform(delete("/employees/" + eid)).andExpect(status().isBadRequest());
        // Ensure nothing has been deleted
        Assert.assertEquals(dataset1, repository.findAll());
    }
}
