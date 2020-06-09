package com.team19.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SprintTest {
    
    private Sprint sprint;
    private Team team;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Date date;

    @Before
    public void setUp() throws ParseException {
        team = new Team(1, "Magic 8 Ball", 44);
        date = dateFormat.parse("2020/01/01");
        sprint = new Sprint("Implement payments", date, 
                2, 11, 11, 1);
    }
    
    @After
    public void taerdown () {
        sprint = null;
    }


    @Test
    public void getSprintDescription() {
        assertEquals("Implement payments", sprint.getSprintDescription());
    }

    @Test
    public void setSprintDescription() {
        sprint.setSprintDescription("Planning sprint");
        assertEquals("Planning sprint", sprint.getSprintDescription());
    }

    @Test
    public void getTeamId() {
        assertEquals(Integer.valueOf(1), sprint.getTeamId());
    }

    @Test
    public void setTeamId() {
        sprint.setTeamId(3);
        assertEquals(Integer.valueOf(3), sprint.getTeamId());
    }

    @Test
    public void getStartDate() {
        assertEquals(date, sprint.getStartDate());
    }

    @Test
    public void setStartDate() throws ParseException {
        Date date = dateFormat.parse("2020/04/07");
        sprint.setStartDate(date);
        assertEquals(date, sprint.getStartDate());
    }

    @Test
    public void getSprintLength() {
        assertEquals(Integer.valueOf(2), sprint.getSprintLength());
    }

    @Test
    public void setSprintLength() {
        sprint.setSprintLength(1);
        assertEquals(Integer.valueOf(1), sprint.getSprintLength());
    }

    @Test
    public void getPointsPlanned() {
        assertEquals(Integer.valueOf(11), sprint.getPointsPlanned());
    }

    @Test
    public void setPointsPlanned() {
        sprint.setPointsPlanned(15);
        assertEquals(Integer.valueOf(15), sprint.getPointsPlanned());
    }

    @Test
    public void getPointsCompleted() {
        assertEquals(Integer.valueOf(11), sprint.getPointsCompleted());
    }

    @Test
    public void setPointsCompleted() {
        sprint.setPointsCompleted(15);
        assertEquals(Integer.valueOf(15), sprint.getPointsCompleted());
    }
}