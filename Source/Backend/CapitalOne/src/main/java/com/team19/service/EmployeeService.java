package com.team19.service;

import com.team19.entity.Employee;
import com.team19.entity.EmployeeLeaveInfo;
import com.team19.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeLeaveInfoService employeeLeaveInfoService;

    public Employee add(Employee employee) {
        String firstName = employee.getFirstName().trim();
        String lastName = employee.getLastName().trim();
        String position = employee.getPosition().trim();
        String email = employee.getEmail().trim();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPosition(position);
        employee.setEmail(email);
        repository.save(employee);
        employeeLeaveInfoService.add(this.createHolidayOverview(employee));
        return employee;
    }

    public Employee findById(Integer eid) {
        return repository.findById(eid).orElse(null);
    }

    /**
     *
     * @param eid Employee's ID
     * @param firstName First name of the employee
     * @param lastName Last Name of the employee
     * @param position Employees position e.g. Associate, Scrum Master, Admin etc.
     * @param email Employee's company email
     * @param teamId ID of the team in which the employee is assigned
     * @return list of employees fetched from datasource
     */
    public List<Employee> findAllBy(
        Integer eid,
        String firstName,
        String lastName,
        String position,
        String email,
        Integer teamId
    ) {
        Employee e = new Employee(
            eid,
            firstName,
            lastName,
            position,
            email,
            teamId
        );
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("eid", exact())
                .withMatcher("firstName", exact())
                .withMatcher("lastName", exact())
                .withMatcher("position", exact())
                .withMatcher("email", exact())
                .withMatcher("teamId", exact());
        Example<Employee> example = Example.of(e, matcher);
        return repository.findAll(example);
    }

    public boolean validate(Employee employee) {
        Integer eid = employee.getEid();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String position = employee.getPosition();
        String email = employee.getEmail();
        Integer teamId = employee.getTeamId();
        List<Object> fields = Arrays.asList(eid, firstName, lastName, position, email, teamId);

        boolean containsNull = fields.stream().anyMatch(f -> f == null || f.toString().equals(""));

        return !containsNull && eid >= 0 && teamId >= 0;
    }

    public boolean eidExistsInTable(Integer eid) {
        return repository.findById(eid).orElse(null) != null;
    }

    public Employee delete(int eid) {

        //Delete Employee's holiday overview
        Employee target = repository.findById(eid).orElse(null);
        if (employeeLeaveInfoService.eidExistsInTable(eid)) {
            employeeLeaveInfoService.delete(eid);
        }
        repository.deleteById(eid);
        return target;
    }

    public EmployeeLeaveInfo createHolidayOverview(Employee employee) {
     EmployeeLeaveInfo employeeLeaveInfo;

     try {
         employeeLeaveInfo = new EmployeeLeaveInfo(employee.getEid());
     }
     catch(Exception e) {
         employeeLeaveInfo = new EmployeeLeaveInfo();
     }

     return employeeLeaveInfo;

    }
}
