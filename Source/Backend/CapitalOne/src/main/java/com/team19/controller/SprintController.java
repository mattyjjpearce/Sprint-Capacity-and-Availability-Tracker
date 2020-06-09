package com.team19.controller;

import com.team19.entity.Sprint;
import com.team19.entity.Team;
import com.team19.service.SprintService;
import com.team19.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/sprints")
public class SprintController {

    private SprintService sprintService;

    public TeamService teamService;

    public SprintController (@Autowired SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Sprint> addSprint (@RequestBody Sprint sprint) {
        if (sprintService.validate(sprint)) {
            System.out.println("Received: " + sprint);
            Sprint result = sprintService.add(sprint);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<Sprint> findAllBy (
            @RequestParam(required = false) String sprintDescription,
            @RequestParam(required = false) Integer teamId,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Integer sprintLength,
            @RequestParam(required = false) Integer pointsPlanned,
            @RequestParam(required = false) Integer pointsCompleted
    ) {
        return sprintService.findAllBy(sprintDescription, teamId, startDate, sprintLength,
                pointsPlanned, pointsCompleted);
    }

    @CrossOrigin
    @GetMapping("/{teamID}")
    public @ResponseBody Iterable<Sprint> findByTeamID(@PathVariable Integer teamID) {

        return sprintService.findAllBy(null, teamID, null,
                null, null, null);
    }

    @CrossOrigin
    @PutMapping("/{sprintId}")
    public ResponseEntity<Sprint> update(@RequestBody Sprint sprint, @PathVariable Integer sprintId) {
        boolean exists = sprintService.sprintIdExistsInTable(sprintId);
        if (!exists) {
            sprint.setSprintId(sprintId);
            return this.addSprint(sprint);
        }

        Sprint resp = sprintService.update(sprintId, sprint);

        if (resp == null) {
            return ResponseEntity.badRequest().build();
        }


        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{sprintId}")
    public ResponseEntity<Sprint> delete(@PathVariable Integer sprintId) {
        if (sprintService.sprintIdExistsInTable(sprintId)) {
            Sprint result = sprintService.delete(sprintId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}
