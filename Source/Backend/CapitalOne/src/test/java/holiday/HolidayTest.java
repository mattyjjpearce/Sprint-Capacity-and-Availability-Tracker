package holiday;

import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
//@WebMvcTest(EmployeeController.class); Works in the baeldung tutorial but not here
public class HolidayTest {

    private Holiday holiday;
    private Employee employee;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private Date date;

    @Before
    public void setUp () throws ParseException {
        date = format.parse( "2020/01/01");
        employee = new Employee(
                75,
                "John",
                "Bonham",
                "Scrum Master",
                "john.bonham@gmail.com",
                5);
        holiday = new Holiday(75, date, 5);
    }

    @After
    public void tearDown () {
        holiday = null;
    }

    @Test
    public void toStringTest () {
        assertEquals("Holiday(75, 2020/01/01, 5)", holiday.toString());

    }

    @Test
    public void getEmployeeIDTest()
    {
        assertEquals(Integer.valueOf(75), holiday.getEmployeeID());
    }

    @Test
    public void setEmployeeIDTest()
    {
        holiday.setEmployeeID(2);
        assertEquals(Integer.valueOf(2), holiday.getEmployeeID());
    }

    @Test
    public void getStartDateTest()
    {
        assertEquals(date, holiday.getStartDate());
    }

    /*@Test
    public void setStartDateTest() throws ParseException {
        Date date = format.parse( "2020/01/02");
        holiday.setStartDate(date);
        assertEquals("2020/01/02", holiday.getStartDateAsString());
    }*/

    @Test
    public void getLengthTest()
    {
        assertEquals(Integer.valueOf(5), holiday.getLength());
    }

    @Test
    public void setLengthTest() {
        holiday.setLength(3);
        assertEquals(Integer.valueOf(3), holiday.getLength());
    }

    /*@Test
    public void getStartDateAsStringTest() {
        assertEquals("2020/01/01", holiday.getStartDateAsString());
    }*/

}
