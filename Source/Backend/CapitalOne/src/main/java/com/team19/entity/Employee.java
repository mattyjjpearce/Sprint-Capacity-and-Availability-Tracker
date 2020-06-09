package com.team19.entity;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A class for storing information about an Employee
 */
@Entity
@Table(name = "Employee")
public class Employee {

    /**
     * The Employee ID. This is used as the ID field in the database, so each
     * employee should have a unique ID
     */
    //TODO: Do we need to do sanity checking for this field? e.g. stop negative values coming in
    @Id
    @Column(name = "EID")
    private Integer eid;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    //TODO: Should this be made an Enum of possible positions within out system e.g. Associate, Scrum Master, Admin, etc.
    @Column(name = "Position")
    private String position;

    //TODO: Does this need to have a standardised format forced upon it e.g. first-last-ID@cap1.com
    @Column(name = "Email")
    private String email;

    @Column(name = "TeamID")
    private Integer teamId;

    /**
     * A constructor for adding all information about the employee upon
     * instantiation
     * @param eid The Employee ID. Should be a unique non-negative integer value
     * @param firstName Employee's first name
     * @param lastName Employee's last name
     * @param position Employee's position within the company e.g. Associate
     * @param email The Employee's work email address
     * @param teamId The ID of the team in which the Employee is currently operating
     */
    public Employee(
        Integer eid,
        String firstName,
        String lastName,
        String position,
        String email,
        Integer teamId
    ) {
        this.eid = eid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.teamId = teamId;
    }

    /**
     * A secondary constructor for when data for the Employee doesn't need to be entered
     * upon instantiation. Individual data fields can be filled in individually using
     * the other methods in this class
     */
    public Employee () {}
    public Integer getEid() { return eid; }
    public void setEid(Integer EID) { this.eid = EID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }

    public String toJson() {
        List<String> fields = Arrays.asList("eid", "firstName", "lastName", "position", "email", "teamId");
        List<Object> objs = Arrays.asList(eid, firstName, lastName, position, email, teamId);

        // Perform zip(fields, objs)
        String jsonRepr = IntStream.range(0, fields.size()).mapToObj(i -> {
            String field = fields.get(i);
            Object obj = objs.get(i);
            return obj == null
                ? ("\"" + field + "\": " + "null")
                : ("\"" + field + "\": " + "\"" + obj.toString() + "\"");
        }).reduce("", (a,b) -> a + "," + b).substring(1);
        return "{" + jsonRepr + "}";
    }

    @Override
    public boolean equals(Object obj) {
        Employee other = (Employee)obj;

        return this.eid.equals(other.eid) &&
               this.firstName.equals(other.firstName) &&
               this.lastName.equals(other.lastName) &&
               this.email.equals(other.email) &&
               this.position.equals(other.position) &&
               this.teamId.equals(other.teamId);
    }

    public static class Builder {
        private Integer eid;
        private String firstName;
        private String lastName;
        private String position;
        private String email;
        private Integer teamId;

        public Employee build() {
            return new Employee(
                eid,
                firstName,
                lastName,
                position,
                email,
                teamId
            );
        }

        public Builder(Integer eid) {
            this.eid = eid;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTeamId(Integer teamId) {
            this.teamId = teamId;
            return this;
        }
    }
}
