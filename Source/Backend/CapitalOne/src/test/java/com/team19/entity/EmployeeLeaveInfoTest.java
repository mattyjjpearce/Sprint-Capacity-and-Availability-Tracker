package com.team19.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmployeeLeaveInfoTest {

    private EmployeeLeaveInfo employeeLeaveInfo, employeeLeaveInfo2, employeeLeaveInfo3;

    @Before
    public void setUp()
    {
        Integer eID = 1, eID2 = 2;
        employeeLeaveInfo = new EmployeeLeaveInfo(eID, 25, 1, 7,
                3, 0, 8, 3, 5,
                5, 5, 5);
        employeeLeaveInfo2 = new EmployeeLeaveInfo(eID2);
        employeeLeaveInfo3 = new EmployeeLeaveInfo();
    }

    @After
    public void tearDown()
    {
        employeeLeaveInfo = null;
        employeeLeaveInfo2 = null;
        employeeLeaveInfo3 = null;
    }

    @Test
    public void getEIDTest()
    {
        Integer eID = 1, eID2 = 2;
        assertEquals(eID, employeeLeaveInfo.getEID());
        assertEquals(eID2, employeeLeaveInfo2.getEID());
        assertEquals(null, employeeLeaveInfo3.getEID());
    }

    @Test
    public void setEIDTest()
    {
        Integer newEID = 9;
        employeeLeaveInfo.setEID(newEID);
        assertEquals(newEID, employeeLeaveInfo.getEID());
    }

    @Test
    public void getTotalDaysLeftTest()
    {
        assertEquals(25, employeeLeaveInfo.getTotalDaysLeft());
        assertEquals(50, employeeLeaveInfo2.getTotalDaysLeft());
        assertEquals(0, employeeLeaveInfo3.getTotalDaysLeft());
    }

    @Test
    public void setTotalDaysLeftTest()
    {
        employeeLeaveInfo.setTotalDaysLeft(30);
        assertEquals(30, employeeLeaveInfo.getTotalDaysLeft());
    }

    @Test
    public void getDaysBookedTest()
    {
        assertEquals(1, employeeLeaveInfo.getDaysBooked());
        assertEquals(0, employeeLeaveInfo2.getDaysBooked());
        assertEquals(0, employeeLeaveInfo3.getDaysBooked());
    }

    @Test
    public void setDaysBookedTest()
    {
        employeeLeaveInfo.setDaysBooked(5);
        assertEquals(5, employeeLeaveInfo.getDaysBooked());
    }

    @Test
    public void getEntitlementTest()
    {
        assertEquals(7, employeeLeaveInfo.getEntitlement());
        assertEquals(10, employeeLeaveInfo2.getEntitlement());
        assertEquals(0, employeeLeaveInfo3.getEntitlement());
    }

    @Test
    public void setEntitlementTest()
    {
        employeeLeaveInfo.setEntitlement(5);
        assertEquals(5, employeeLeaveInfo.getEntitlement());
    }

    @Test
    public void getHolidaysPurchasedTest()
    {
        assertEquals(3, employeeLeaveInfo.getHolidaysPurchased());
        assertEquals(0, employeeLeaveInfo2.getHolidaysPurchased());
        assertEquals(0, employeeLeaveInfo3.getHolidaysPurchased());
    }

    @Test
    public void setHolidaysPurchasedTest()
    {
        employeeLeaveInfo.setHolidaysPurchased(6);
        assertEquals(6, employeeLeaveInfo.getHolidaysPurchased());
    }

    @Test
    public void getHolidaysSoldTest()
    {
        assertEquals(0, employeeLeaveInfo.getHolidaysSold());
        assertEquals(0, employeeLeaveInfo2.getHolidaysSold());
        assertEquals(0, employeeLeaveInfo3.getHolidaysSold());
    }

    @Test
    public void setHolidaysSoldTest()
    {
        employeeLeaveInfo.setHolidaysSold(10);
        assertEquals(10, employeeLeaveInfo.getHolidaysSold());
    }

    @Test
    public void getDaysCarriedFromLastYearTest()
    {
        assertEquals(8, employeeLeaveInfo.getDaysCarriedFromLastYr());
        assertEquals(0, employeeLeaveInfo2.getDaysCarriedFromLastYr());
        assertEquals(0, employeeLeaveInfo3.getDaysCarriedFromLastYr());
    }

    @Test
    public void setDaysCarriedFromLastYearTest()
    {
        employeeLeaveInfo.setDaysCarriedFromLastYr(9);
        assertEquals(9, employeeLeaveInfo.getDaysCarriedFromLastYr());
    }

    @Test
    public void getDaysCarriedForwardNextYearTest()
    {
        assertEquals(3, employeeLeaveInfo.getDaysCarriedFwrdNextYr());
        assertEquals(0, employeeLeaveInfo2.getDaysCarriedFwrdNextYr());
        assertEquals(0, employeeLeaveInfo3.getDaysCarriedFwrdNextYr());
    }

    @Test
    public void setDaysCarriedForwardNextYearTest()
    {
        employeeLeaveInfo.setDaysCarriedFwrdNextYr(1);
        assertEquals(1, employeeLeaveInfo.getDaysCarriedFwrdNextYr());
    }

    @Test
    public void getFamilyDaysTest()
    {
        assertEquals(5, employeeLeaveInfo.getFamilyDays());
        assertEquals(5, employeeLeaveInfo2.getFamilyDays());
        assertEquals( 0, employeeLeaveInfo3.getFamilyDays());
    }

    @Test
    public void setFamilyDaysTest()
    {
        employeeLeaveInfo.setFamilyDays(5);
        assertEquals(5, employeeLeaveInfo.getFamilyDays());
    }


    @Test
    public void getEmergencyDaysTest()
    {
        assertEquals(5, employeeLeaveInfo.getEmergencyDays());
        assertEquals(5, employeeLeaveInfo2.getEmergencyDays());
        assertEquals(0, employeeLeaveInfo3.getEmergencyDays());
    }

    @Test
    public void setEmergencyDaysTest()
    {
        employeeLeaveInfo.setEmergencyDays(4);
        assertEquals(4, employeeLeaveInfo.getEmergencyDays());
    }

    @Test
    public void getSickDaysTest()
    {
        assertEquals(5, employeeLeaveInfo.getSickDays());
        assertEquals(10, employeeLeaveInfo2.getSickDays());
        assertEquals(0, employeeLeaveInfo3.getSickDays());
    }

    @Test
    public void setSickDaysTest()
    {
        employeeLeaveInfo.setSickDays(4);
        assertEquals(4, employeeLeaveInfo.getSickDays());
    }

    @Test
    public void getParentalLeaveDaysTest()
    {
        assertEquals(5, employeeLeaveInfo.getParentalLeaveDays());
        assertEquals(5, employeeLeaveInfo2.getParentalLeaveDays());
        assertEquals(0, employeeLeaveInfo3.getParentalLeaveDays());
    }

    @Test
    public void setParentalLeaveDaysTest()
    {
        employeeLeaveInfo.setParentalLeaveDays(4);
        assertEquals(4, employeeLeaveInfo.getParentalLeaveDays());
    }

    @Test
    public void printHolidayOverviewToStringTest()
    {
        assertEquals(("Holiday Overview: \n"
                        + "EID: 2" + "\n"
                        + "Total Days Left: 50" + "\n"
                        + "Days Booked: 0" + "\n"
                        + "Entitlement: 10" + "\n"
                        + "Holidays Purchased: 0" +  "\n"
                        + "Holidays Sold: 0" +  "\n"
                        + "Days Carried From Last Year: 0" + "\n"
                        + "Days To Be Carried To Next Year: 0" + "\n"
                        + "Family Days: 5" + "\n"
                        + "Emergency Days: 5"  + "\n"
                        + "Sick Days: 10" +  "\n"
                        + "Parental Leave Days: 5" + "\n")
                , employeeLeaveInfo2.printHolidayOverviewToString());
    }

}