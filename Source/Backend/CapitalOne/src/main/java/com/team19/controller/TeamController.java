package com.team19.controller;

import com.team19.entity.Employee;
import com.team19.entity.Team;
import com.team19.service.EmployeeService;
import com.team19.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    TeamService teamService;

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<Team> findAllBy(
            @RequestParam(required = false) Integer teamID,
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) Integer teamManagerID
    ) {
        return teamService.findAllBy(teamID, teamName, teamManagerID);
    }

    @CrossOrigin
    @GetMapping("/{teamID}")
    public ResponseEntity<Team> findById(@PathVariable Integer teamID) {
        Team team = teamService.findById(teamID);

        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Team> add(@RequestBody Team team) {
        if (teamService.validate(team) &&
                !teamService.eidExistsInTable(team.getTeamId()) &&
                employeeService.eidExistsInTable(team.getTeamManagerId())) {
            teamService.add(team);

            Employee teamManager = employeeService.findAllBy(
                    team.getTeamManagerId(),
                    null,
                    null,
                    null,
                    null,
                    null
            ).get(0);

            team.setTeamManager(teamManager);

            return new ResponseEntity<>(team, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> put(@PathVariable Integer teamId, @RequestBody Team team) {
        boolean exists = teamService.eidExistsInTable(teamId);
        if (!exists) {
            team.setTeamId(teamId);
            return this.add(team);
        }
        Team resp = teamService.put(teamId, team);

        if (resp == null) {
            // resp is null -> bad parameter
            return ResponseEntity.badRequest().build();
        }

        // Manually assign the team manager according to teamManagerId
        // In order for the response code to be
        // { "teamManager": { employee's info } } instead of null

        Employee teamManager = employeeService.findAllBy(
                team.getTeamManagerId(),
                null,
                null,
                null,
                null,
                null
        ).get(0);

        resp.setTeamManager(teamManager);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Team> delete(@PathVariable Integer teamId) {
        Team resp = teamService.delete(teamId);
        if (resp == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{teamId}/manager")
    public ResponseEntity<Employee> getTeamManager(@PathVariable Integer teamId) {
        Team team = teamService.findById(teamId);
        if (team == null) {
            return ResponseEntity.badRequest().build();
        }
        Employee teamManager = team.getTeamManager();
        if (teamManager == null) {
            // When manager is not set
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(team.getTeamManager(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{teamId}/employees")
    public ResponseEntity<Iterable<Employee>> getTeamMembers(@PathVariable Integer teamId) {
        Iterable<Employee> employees = employeeService.findAllBy(
                null,
                null,
                null,
                null,
                null,
                teamId
        );
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
