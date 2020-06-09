package com.team19.controller;

import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import com.team19.entity.Team;
import com.team19.service.EmployeeService;
import com.team19.service.HolidayService;
import com.team19.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    private HolidayService holidayService;

    @Autowired
    private TeamService teamService;

    public EmployeeController(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin
    @GetMapping("/{eid}")
    public ResponseEntity<Employee> findEmployeeByEid(@PathVariable Integer eid) {
        Employee result = employeeService.findById(eid);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     *
     * @param eid Employee's ID
     * @param firstName First name of the employee
     * @param lastName Last Name of the employee
     * @param position Employees position e.g. Associate, Scrum Master, Admin etc.
     * @param email Employee's company email
     * @param teamId ID of the team in which the employee is assigned
     * @return HTTP 200 (OK) indicating the targeted list has been fetched
     */
    //holidayOverview
    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<Employee> findAllBy(
        @RequestParam(required = false) Integer eid,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName,
        @RequestParam(required = false) String position,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Integer teamId
    ) {
        return employeeService.findAllBy(eid, firstName, lastName, position, email, teamId);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        if (employeeService.validate(employee) &&
            !employeeService.eidExistsInTable(employee.getEid())) {
            Employee result = employeeService.add(employee);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Employee> put(@RequestBody Employee employee) {
        if (employeeService.validate(employee)) {
            boolean exists = employeeService.eidExistsInTable(employee.getEid());
            Employee result = employeeService.add(employee);
            return exists ? new ResponseEntity<>(result, HttpStatus.OK)
                          : new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{eid}")
    public ResponseEntity<Employee> delete(@PathVariable Integer eid) {
        if (employeeService.eidExistsInTable(eid)) {
            Employee result = employeeService.delete(eid);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @GetMapping("/{eid}/holidays")
    public ResponseEntity<Iterable<Holiday>> getHolidayByEmployee(@PathVariable Integer eid) {
        if (employeeService.eidExistsInTable(eid)) {
            List<Holiday> holidays = holidayService.findAllBy(eid, null, null);
            return new ResponseEntity<>(holidays, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @GetMapping("/{eid}/team")
    public ResponseEntity<Team> getTeamByEmployee(@PathVariable Integer eid) {
        if (employeeService.eidExistsInTable(eid)) {
            Employee employee = employeeService.findById(eid);
            Integer teamId = employee.getTeamId();
            Team team = teamService.findById(teamId);
            if (team == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
