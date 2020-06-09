package employee;

import com.team19.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class); Works in the baeldung tutorial but not here
public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp () {
        employee = new Employee();
    }

    @After
    public void tearDown () {
        employee = null;
    }

    @Test
    public void getIDTest () {
        employee.setEid(1);
        assertEquals(Integer.valueOf(1), employee.getEid());
    }

    @Test
    public void setIDTest () {
        employee.setEid(2);
        assertEquals(Integer.valueOf(2), employee.getEid());
    }

    @Test
    public void getFirstNameTest () {
        employee.setFirstName("John");
        assertEquals("John", employee.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        employee.setFirstName("Jamie");
        assertEquals("Jamie", employee.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        employee.setLastName("Blomfield");
        assertEquals("Blomfield", employee.getLastName());
    }

    @Test
    public void setLastNameTest() {
        employee.setLastName("Potter");
        assertEquals("Potter", employee.getLastName());
    }

    @Test
    public void getPositionTest() {
        employee.setPosition("Associate");
        assertEquals("Associate", employee.getPosition());
    }

    @Test
    public void setPositionTest() {
        employee.setPosition("Scrum Master");
        assertEquals("Scrum Master", employee.getPosition());
    }

    @Test
    public void getEmailTest() {
        employee.setEmail("JohnSmith@Cap1.com");
        assertEquals("JohnSmith@Cap1.com", employee.getEmail());
    }

    @Test
    public void setEmailTest() {
        employee.setEmail("JaneDoe@Cap1.com");
        assertEquals("JaneDoe@Cap1.com", employee.getEmail());
    }

    @Test
    public void getTeamIDTest() {
        employee.setTeamId(1);
        assertEquals(Integer.valueOf(1), employee.getTeamId());
    }

    @Test
    public void setTeamIDTest() {
        employee.setTeamId(2);
        assertEquals(Integer.valueOf(2), employee.getTeamId());
    }

}