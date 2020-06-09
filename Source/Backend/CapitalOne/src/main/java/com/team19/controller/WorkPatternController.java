package com.team19.controller;

import com.team19.entity.WorkPattern;
import com.team19.service.WorkPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/workPattern")
public class WorkPatternController {

    private WorkPatternService service;

    public WorkPatternController(@Autowired WorkPatternService service) {
        this.service = service;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<WorkPattern> addWorkPattern (@RequestBody WorkPattern pattern) {
        if (service.validate(pattern) && !(service.employeeIDExistsInTable(pattern.getEid()))) {
            System.out.println("Received:" + pattern);
            WorkPattern result = service.add(pattern);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<WorkPattern> findAllBy (
            @RequestParam(required = false) Integer eid,
            @RequestParam(required = false) Integer mondayHours,
            @RequestParam(required = false) Integer tuesdayHours,
            @RequestParam(required = false) Integer wednesdayHours,
            @RequestParam(required = false) Integer thursdayHours,
            @RequestParam(required = false) Integer fridayHours
    ) {
        return service.findAllBy(eid, mondayHours, tuesdayHours,
                wednesdayHours, thursdayHours, fridayHours);
    }

    @CrossOrigin
    @PutMapping("/{workPatternId}")
    public ResponseEntity<WorkPattern> put(@PathVariable Integer workPatternId, @RequestBody WorkPattern pattern) {
        boolean exists = service.workPatternIdExistsInTable(workPatternId);
        if (!exists) {
            pattern.setWorkPatternId(workPatternId);
            return this.addWorkPattern(pattern);
        }
        WorkPattern resp = service.update(workPatternId, pattern);

        if (resp == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{workPatternId}")
    public ResponseEntity<WorkPattern> delete(@PathVariable Integer workPatternId) {
        if (service.employeeIDExistsInTable(workPatternId)) {
            WorkPattern result = service.delete(workPatternId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
