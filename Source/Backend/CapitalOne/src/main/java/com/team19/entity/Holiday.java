package com.team19.entity;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

/**
 * A class that stores the data for a holiday to be taken by an associate.
 */
@Entity
@Table(name = "Holiday")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HolidayID")
    private Integer holidayId;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "Length")
    private Integer length;

    @OneToOne(targetEntity = com.team19.entity.Employee.class) //cascade = CascadeType.ALL//)
    @JoinColumn(name="employeeID", referencedColumnName="EID", nullable = false)
    public Employee employee;

    //@Transient
    //private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public Holiday(Integer employeeID, Date StartDate, Integer Length) {
        this.employee = new Employee();
        setEmployeeID(employeeID);
        setStartDate(StartDate);
        setLength(Length);
    }

    public Holiday() {employee = new Employee();}

    public String toString() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return "Holiday(" + this.getEmployeeID() + ", " + startDate + ", " + length + ")";
    }

    public Integer getHolidayId() { return holidayId; }
    public Integer getEmployeeID() { return employee.getEid(); }
    public void setEmployeeID(Integer employeeID) { this.employee.setEid(employeeID); }
    public Date getStartDate() { return startDate; }
    //public String getStartDateAsString() { return sdf.format(this.startDate); }
    public Employee getEmployee() { return this.employee; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Integer getLength() { return length; }
    public void setLength(Integer Length) { this.length = Length; }
    public void setHolidayId(Integer HolidayId) {this.holidayId = HolidayId;}

    public String toJson() {
        List<String> fields = Arrays.asList("holidayId", "startDate", "length");
        List<Object> objs = Arrays.asList(holidayId, startDate, length);

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

    /*@Override
    public boolean equals(Object obj) {
        Holiday other = (Holiday) obj;

        return this.holidayId.equals(other.holidayId) &&
                this.startDate.equals(other.startDate) &&
                this.length.equals(other.length);
    }*/

    public static class Builder {
        private Integer holidayId;
        private Date startDate;
        private Integer length;
        private Integer employeeID;

        public Holiday build() {
            return new Holiday(
                    employeeID,
                    startDate,
                    length
            );
        }

        public Builder(Integer holidayId) {
            this.holidayId = holidayId;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withLength(Integer length) {
            this.length = length;
            return this;
        }

        public Builder withEmployeeID(Integer employeeID) {
            this.employeeID = employeeID;
            return this;
        }
    }


}
