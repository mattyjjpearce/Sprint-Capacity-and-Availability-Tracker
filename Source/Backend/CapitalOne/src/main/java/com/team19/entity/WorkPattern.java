package com.team19.entity;

import javax.persistence.*;

/**
 * Class that stores information about the work pattern an employee follows
 */
@Entity
@Table(name = "WorkPattern")
public class WorkPattern {

    /**
     * Uses the EID of the employee that 'owns' this working pattern
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WorkPatternId")
    private Integer workPatternId;

    //TODO: Set upper and lower bounds on value for each days working hours
    /**
     * The below fields are used to store the number of hours an emnployee works on that particular day
     */
    @Column(name = "Monday")
    private Integer monday;

    @Column(name = "Tuesday")
    private Integer tuesday;

    @Column(name = "Wednesday")
    private Integer wednesday;

    @Column(name = "Thursday")
    private Integer thursday;

    @Column(name = "Friday")
    private Integer friday;

    @OneToOne(targetEntity = com.team19.entity.Employee.class)
    @JoinColumn(name = "EmployeeId", referencedColumnName = "EID", nullable = false)
    public Employee employee;

    /**
     * Stores the total number of hours an employee works in a week. Cannot go above ... or below ...
     */
    //TODO: Use for sanity checking upper nad lower boundaries for working weeks
    //private int total;

    /**
     * Constructor for when all data is known upon instantiation
     * @param eid
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     */
    public WorkPattern(
            Integer eid,
            Integer monday,
            Integer tuesday,
            Integer wednesday,
            Integer thursday,
            Integer friday
    )
    {
        this.employee = new Employee();
        setEid(eid);
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        //updateTotalHours();
    }

    /**
     * Constructor for when only eid is known. All other values are set to null;
     * @param eid
     */
    public WorkPattern(Integer eid)
    {
        this.employee = new Employee();
        setEid(eid);
        monday = null;
        tuesday = null;
        wednesday = null;
        thursday = null;
        friday = null;
        //updateTotalHours();
    }

    //TODO: Needed?
    /**
     * If no values are known. All values are set to null
     */
    public WorkPattern()
    {
        this.employee = new Employee();
        setEid(null);
        monday = null;
        tuesday = null;
        wednesday = null;
        thursday = null;
        friday = null;
        //updateTotalHours();
    }

    public Integer getWorkPatternId() {
        return workPatternId;
    }

    public void setWorkPatternId(Integer workPatternId) {
        this.workPatternId = workPatternId;
    }
    public Integer getEid(){return employee.getEid();}
    public void setEid(Integer employeeId){this.employee.setEid(employeeId); /*updateTotalHours()*/;}
    public Integer getMondayHours(){return this.monday;}
    public void setMondayHours(Integer newMondayHours){this.monday = newMondayHours; /*updateTotalHours()*/;}
    public Integer getTuesdayHours(){return this.tuesday;}
    public void setTuesdayHours(Integer newTuesdayHours){this.tuesday = newTuesdayHours; /*updateTotalHours()*/;}
    public Integer getWednesdayHours(){return this.wednesday;}
    public void setWednesdayHours(Integer newWednesdayHours){this.wednesday = newWednesdayHours; /*updateTotalHours()*/;}
    public Integer getThursdayHours(){return this.thursday;}
    public void setThursdayHours(Integer newThursdayHours){this.thursday = newThursdayHours; /*updateTotalHours()*/;}
    public Integer getFridayHours(){return this.friday;}
    public void setFridayHours(Integer newFridayHours){ this.friday = newFridayHours; /*updateTotalHours()*/;}
    //public int getTotalHours(){return this.total;}
    /*public void updateTotalHours()
    {
        total = 0;

        if(monday != null)
            total += (int) monday;
        if(tuesday != null)
            total += (int) tuesday;
        if(wednesday != null)
            total += (int) wednesday;
        if(thursday != null)
            total += (int) thursday;
        if(friday != null)
            total += friday;
    }*/

    public static class Builder
    {
        private Integer eID;
        private Integer monday;
        private Integer tuesday;
        private Integer wednesday;
        private Integer thursday;
        private Integer friday;

        public WorkPattern build()
        {
            return new WorkPattern(
                    eID,
                    monday,
                    tuesday,
                    wednesday,
                    thursday,
                    friday
            );
        }

        public Builder(Integer eID) {this.eID = eID;}

        public Builder withMondayHours(Integer monday)
        {
            this.monday = monday;
            return this;
        }
        public Builder withTuesdayHours(Integer tuesday)
        {
            this.tuesday = tuesday;
            return this;
        }
        public Builder withWednesdayHours(Integer wednesday)
        {
            this.wednesday = wednesday;
            return this;
        }
        public Builder withThursdayHours(Integer thursday)
        {
            this.thursday = thursday;
            return this;
        }
        public Builder withFridayHours(Integer friday)
        {
            this.friday = friday;
            return this;
        }
    }
}
