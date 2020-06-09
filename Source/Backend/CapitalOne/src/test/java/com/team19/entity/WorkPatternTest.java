package com.team19.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WorkPatternTest {

    private WorkPattern pattern1, pattern2, pattern3;

    @Before
    public void setup()
    {
        pattern1 = new WorkPattern(1, 8, 8, 8, 8, 8);
        pattern2 = new WorkPattern(2);
        pattern3 = new WorkPattern();
    }

    @After
    public void tearDown()
    {
        pattern1 = null;
        pattern2 = null;
        pattern3 = null;
    }

    @Test
    public void getEidTest()
    {
        Integer eID1 = 1, eID2 = 2;
        assertEquals(eID1, pattern1.getEid());
        assertEquals(eID2, pattern2.getEid());
        assertNull(pattern3.getEid());
    }

    @Test
    public void setEidTest()
    {
        Integer eID1 = 5, eID2 = 10, eID3 = 15;
        pattern1.setEid(eID1);
        pattern2.setEid(eID2);
        pattern3.setEid(eID3);

        assertEquals(eID1, pattern1.getEid());
        assertEquals(eID2, pattern2.getEid());
        assertEquals(eID3, pattern3.getEid());
    }

    @Test
    public void getMondayHoursTest()
    {
        Integer hours1 = 8;
        assertEquals(hours1, pattern1.getMondayHours());
        assertNull(pattern2.getMondayHours());
        assertNull(pattern3.getMondayHours());
    }

    @Test
    public void setMondayHoursTest()
    {
        Integer hours1 = 5, hours2 = 10, hours3 = 15;
        pattern1.setMondayHours(hours1);
        pattern2.setMondayHours(hours2);
        pattern3.setMondayHours(hours3);

        assertEquals(hours1, pattern1.getMondayHours());
        assertEquals(hours2, pattern2.getMondayHours());
        assertEquals(hours3, pattern3.getMondayHours());
    }

    @Test
    public void getTuesdayHoursTest()
    {
        Integer hours1 = 8;
        assertEquals(hours1, pattern1.getTuesdayHours());
        assertNull(pattern2.getTuesdayHours());
        assertNull(pattern3.getTuesdayHours());
    }

    @Test
    public void setTuesdayHoursTest()
    {
        Integer hours1 = 5, hours2 = 10, hours3 = 15;
        pattern1.setTuesdayHours(hours1);
        pattern2.setTuesdayHours(hours2);
        pattern3.setTuesdayHours(hours3);

        assertEquals(hours1, pattern1.getTuesdayHours());
        assertEquals(hours2, pattern2.getTuesdayHours());
        assertEquals(hours3, pattern3.getTuesdayHours());
    }

    @Test
    public void getWednesdayHoursTest()
    {
        Integer hours1 = 8;
        assertEquals(hours1, pattern1.getWednesdayHours());
        assertNull(pattern2.getWednesdayHours());
        assertNull(pattern3.getWednesdayHours());
    }

    @Test
    public void setWednesdayHoursTest()
    {
        Integer hours1 = 5, hours2 = 10, hours3 = 15;
        pattern1.setWednesdayHours(hours1);
        pattern2.setWednesdayHours(hours2);
        pattern3.setWednesdayHours(hours3);

        assertEquals(hours1, pattern1.getWednesdayHours());
        assertEquals(hours2, pattern2.getWednesdayHours());
        assertEquals(hours3, pattern3.getWednesdayHours());
    }

    @Test
    public void getThursdayHoursTest()
    {
        Integer hours1 = 8;
        assertEquals(hours1, pattern1.getThursdayHours());
        assertNull(pattern2.getThursdayHours());
        assertNull(pattern3.getThursdayHours());
    }

    @Test
    public void setThursdayHoursTest()
    {
        Integer hours1 = 5, hours2 = 10, hours3 = 15;
        pattern1.setThursdayHours(hours1);
        pattern2.setThursdayHours(hours2);
        pattern3.setThursdayHours(hours3);

        assertEquals(hours1, pattern1.getThursdayHours());
        assertEquals(hours2, pattern2.getThursdayHours());
        assertEquals(hours3, pattern3.getThursdayHours());
    }

    @Test
    public void getFridayHoursTest()
    {
        Integer hours1 = 8;
        assertEquals(hours1, pattern1.getFridayHours());
        assertNull(pattern2.getFridayHours());
        assertNull(pattern3.getFridayHours());
    }

    @Test
    public void setFridayHoursTest()
    {
        Integer hours1 = 5, hours2 = 10, hours3 = 15;
        pattern1.setFridayHours(hours1);
        pattern2.setFridayHours(hours2);
        pattern3.setFridayHours(hours3);

        assertEquals(hours1, pattern1.getFridayHours());
        assertEquals(hours2, pattern2.getFridayHours());
        assertEquals(hours3, pattern3.getFridayHours());
    }




}
