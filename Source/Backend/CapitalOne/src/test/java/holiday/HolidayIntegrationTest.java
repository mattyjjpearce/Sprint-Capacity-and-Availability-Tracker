package holiday;

import com.team19.Main;
import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import com.team19.repository.EmployeeRepository;
import com.team19.repository.HolidayRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Main.class, config.H2TestProfileConfig.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class HolidayIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HolidayRepository repository;

    @Autowired
    private MockMvc mvc;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    public HolidayIntegrationTest() throws ParseException {}

    private List<Employee> employees = Arrays.asList(
            new Employee.Builder(0)
                .withFirstName("Jack")
                .build(),

            new Employee.Builder(1)
                .withFirstName("Jimmy")
                .build(),

            new Employee.Builder(2)
                .withFirstName("Liam")
                .build()
    );


    private List<Holiday> dataset1 = Arrays.asList(
        new Holiday.Builder(1)
            .withStartDate(new Date("2020/06/02"))
            .withLength(5)
            .withEmployeeID(0)
            .build(),

        new Holiday.Builder(2)
            .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/07/25"))
            .withLength(15)
            .withEmployeeID(1)
            .build(),

        new Holiday.Builder(3)
            .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/04"))
            .withLength(7)
            .withEmployeeID(1)
            .build(),

        new Holiday.Builder(4)
            .withStartDate(new SimpleDateFormat("yyyy/MM/dd").parse("2020/07/28"))
            .withLength(6)
            .withEmployeeID(2)
            .build()
    );



    @Before
    public void setUp() {
        repository.deleteAll();
        employeeRepository.saveAll(employees);
        repository.saveAll(dataset1);

    }

    @Test
    public void testGetAllHoliday() throws Exception {

        mvc.perform(get("/holidays"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].holidayId").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate").value("2020/06/02"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].length").value(5))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].holidayId").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].startDate").value("2020/07/25"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].length").value(15))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[2].holidayId").value(3))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[2].startDate").value("2020/06/04"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[2].length").value(7))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[3].holidayId").value(4))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[3].startDate").value("2020/07/28"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[3].length").value(6))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[4]").doesNotExist());
    }



}
