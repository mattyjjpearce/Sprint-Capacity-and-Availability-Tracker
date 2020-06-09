package com.team19.entity;


import javax.persistence.*;

/**
 * A class for storing information about the types of holiday an employee has
 */
@Entity
@Table(name = "EmployeeLeaveInfo")
public class EmployeeLeaveInfo {

    /**
     * The Employee ID. This is used as the ID field in the database, so each
     * employee should have a unique ID
     */
    @Id
    @Column(name = "EID")
    public Integer EID;

    @OneToOne(targetEntity = com.team19.entity.Employee.class)
    @JoinColumn(name="EID", referencedColumnName="EID", nullable = false)
    public Employee employee;

    @Column(name = "TotalDaysLeft")
    private Integer totalDaysLeft;
    @Column(name = "DaysBooked")
    private Integer daysBooked;
    @Column(name = "Entitlement")
    private Integer entitlement;
    @Column(name = "HolidaysPurchased")
    private Integer holidaysPurchased;
    @Column(name = "HolidaysSold")
    private Integer holidaysSold;
    /**
     * Holiday days carried over from last year
     */
    @Column(name = "DaysCarriedFromLastYr")
    private Integer DaysCarriedFromLastYr;
    /**
     * Holiday days that will be carried into next year
     */
    @Column(name = "DaysCarriedFwrdNextYr")
    private Integer DaysCarriedFwrdNextYr;
    @Column(name = "FamilyDays")
    private Integer familyDays;
    @Column(name = "EmergencyDays")
    private Integer emergencyDays;
    @Column(name = "SickDays")
    private Integer sickDays;
    @Column(name = "ParentalLeaveDays")
    private Integer parentalLeaveDays;

    /**
     * Constructor for when all parameters are to be entered upon instantiation
     * @param EID Employee ID - Unique non-negative value
     * @param totalDaysLeft Total number of holiday days that can be booked off by the employee
     * @param daysBooked Total number of days the employee has booked off
     * @param entitlement ??
     * @param holidaysPurchased Number of holiday days the employee has purchased off of other employees
     * @param holidaysSold Number of holiday days the employee has sold to other employees
     * @param DaysCarriedFromLastYr Number of holiday days that have been carried over from last year
     * @param DaysCarriedFwrdNextYr Number of holiday days that are to be carried onto next year
     * @param emergencyDays Type of holiday that can be booked off - Last minute holiday for emergencies
     * @param sickDays Type of holiday that can be booked off - Paid leave for when the employee is too ill to come to work
     * @param parentalLeaveDays Type of holiday that can be booked off - Holiday for parents when they need to book off time
     */
    public EmployeeLeaveInfo(Integer EID, Integer totalDaysLeft, Integer daysBooked, Integer entitlement,
                             Integer holidaysPurchased, Integer holidaysSold, Integer DaysCarriedFromLastYr,
                             Integer DaysCarriedFwrdNextYr, Integer familyDays, Integer emergencyDays, Integer sickDays,
                             Integer parentalLeaveDays)
    {
        this.employee = new Employee();
        setEID(EID);
        setTotalDaysLeft(totalDaysLeft);
        setDaysBooked(daysBooked);
        setEntitlement(entitlement);
        setHolidaysPurchased(holidaysPurchased);
        setHolidaysSold(holidaysSold);
        setDaysCarriedFromLastYr(DaysCarriedFromLastYr);
        setDaysCarriedFwrdNextYr(DaysCarriedFwrdNextYr);
        setFamilyDays(familyDays);
        setEmergencyDays(emergencyDays);
        setSickDays(sickDays);
        setParentalLeaveDays(parentalLeaveDays);

    }

    /**
     * Constructor that only requires an employee ID. Other fields will be set to default values
     * @param EID Employee ID
     */
    public EmployeeLeaveInfo(Integer EID)
    {
        setEID(EID);
        setTotalDaysLeft(50);
        setDaysBooked(0);
        setEntitlement(10);
        setHolidaysPurchased(0);
        setHolidaysSold(0);
        setDaysCarriedFwrdNextYr(0);
        setDaysCarriedFromLastYr(0);
        setFamilyDays(5);
        setEmergencyDays(5);
        setSickDays(10);
        setParentalLeaveDays(5);
    }

    /**
     * Constructor will set EID to null and all other values to 0
     */
    public EmployeeLeaveInfo()
    {
        setEID(null);
        setTotalDaysLeft(0);
        setDaysBooked(0);
        setEntitlement(0);
        setHolidaysPurchased(0);
        setHolidaysSold(0);
        setDaysCarriedFwrdNextYr(0);
        setDaysCarriedFromLastYr(0);
        setFamilyDays(0);
        setEmergencyDays(0);
        setSickDays(0);
        setParentalLeaveDays(0);
    }

    /**
     * Returns the employee's ID
     * @return Employee's ID as Integer object
     */
    public Integer getEID()
    {
        return this.EID;
    }

    /**
     * Allows the the employee's ID to be overwritten
     * @param newEID Requires an Integer object to ovewrite the current EID
     */
    public void setEID(Integer newEID)
    {
        this.EID = newEID;
    }

    public int getTotalDaysLeft()
    {
        return this.totalDaysLeft;
    }
    public void setTotalDaysLeft(int newDaysLeft)
    {
        this.totalDaysLeft = newDaysLeft;
    }

    public int getDaysBooked()
    {
        return this.daysBooked;
    }
    public void setDaysBooked(int newDaysBooked)
    {
        this.daysBooked = newDaysBooked;
    }

    public int getEntitlement()
    {
        return this.entitlement;
    }
    public void setEntitlement(int newEntitlement)
    {
        this.entitlement = newEntitlement;
    }

    public int getHolidaysPurchased()
    {
        return this.holidaysPurchased;
    }
    public void setHolidaysPurchased(int newHolidaysPurchased)
    {
        this.holidaysPurchased = newHolidaysPurchased;
    }

    public int getHolidaysSold()
    {
        return this.holidaysSold;
    }
    public void setHolidaysSold(int newHolidaysSold)
    {
        this.holidaysSold = newHolidaysSold;
    }

    public int getDaysCarriedFromLastYr()
    {
        return this.DaysCarriedFromLastYr;
    }
    public void setDaysCarriedFromLastYr(int newDaysCarriedFromLastYear)
    {
        this.DaysCarriedFromLastYr = newDaysCarriedFromLastYear;
    }

    public int getDaysCarriedFwrdNextYr()
    {
        return  this.DaysCarriedFwrdNextYr;
    }
    public void setDaysCarriedFwrdNextYr(int newDaysCarriedForwardNextYear)
    {
        this.DaysCarriedFwrdNextYr = newDaysCarriedForwardNextYear;
    }

    public int getFamilyDays()
    {
        return this.familyDays;
    }
    public void setFamilyDays(int newFamilyDays)
    {
        this.familyDays = newFamilyDays;
    }

    public int getEmergencyDays()
    {
        return this.emergencyDays;
    }
    public void setEmergencyDays(int newEmergencyDays)
    {
        this.emergencyDays= newEmergencyDays;
    }

    public int getSickDays()
    {
        return this.sickDays;
    }
    public void setSickDays(int newSickDays)
    {
        this.sickDays = newSickDays;
    }

    public int getParentalLeaveDays()
    {
        return this.parentalLeaveDays;
    }
    public void setParentalLeaveDays(int newParentalLeaveDays)
    {
        this.parentalLeaveDays = newParentalLeaveDays;
    }

    /**
     * Prints the Holiday Overview as a formatted string, with each attribute labelled and on a new line
     * @return Formatted string listing values of all attributes
     */
    public String printHolidayOverviewToString()
    {
        return ("Holiday Overview: \n"
                + "EID: " + getEID() + "\n"
                + "Total Days Left: " + getTotalDaysLeft() + "\n"
                + "Days Booked: " + getDaysBooked() + "\n"
                + "Entitlement: " + getEntitlement() + "\n"
                + "Holidays Purchased: " + getHolidaysPurchased() + "\n"
                + "Holidays Sold: " + getHolidaysSold() + "\n"
                + "Days Carried From Last Year: " + getDaysCarriedFromLastYr() + "\n"
                + "Days To Be Carried To Next Year: " + getDaysCarriedFwrdNextYr() + "\n"
                + "Family Days: " + getFamilyDays() + "\n"
                + "Emergency Days: " + getEmergencyDays() + "\n"
                + "Sick Days: " + getSickDays() + "\n"
                + "Parental Leave Days: " + getParentalLeaveDays() + "\n");
    }

    public static class Builder {
        private Integer EID;

        public EmployeeLeaveInfo build() {
            return  new EmployeeLeaveInfo(EID);
        }

        public Builder(Integer EID) {
            this.EID = EID;
        }
    }

}
