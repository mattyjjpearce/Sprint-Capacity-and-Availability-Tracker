package com.team19.controller;

import com.team19.entity.EmployeeLeaveInfo;
import com.team19.service.EmployeeLeaveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/employeeLeaveInfo")
public class EmployeeLeaveInfoController {

    private EmployeeLeaveInfoService employeeLeaveInfoService;

    public EmployeeLeaveInfoController(@Autowired EmployeeLeaveInfoService employeeLeaveInfoService) {
        this.employeeLeaveInfoService = employeeLeaveInfoService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> add(@RequestBody EmployeeLeaveInfo employeeLeaveInfo)
   {
       if (employeeLeaveInfoService.validate(employeeLeaveInfo)) {
           employeeLeaveInfoService.add(employeeLeaveInfo);
           return ResponseEntity.status(HttpStatus.CREATED).build();
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }

    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<EmployeeLeaveInfo> findAllBy(
            @RequestParam(required = false) Integer employeeId
    ) {
        return employeeLeaveInfoService.findAllBy(employeeId);
    }

    @CrossOrigin
    @GetMapping("/{EID}")
    public @ResponseBody Iterable<EmployeeLeaveInfo> findByEmployeeID(
            @PathVariable Integer EID
    ) {
        return employeeLeaveInfoService.findAllBy(EID);
    }

    @CrossOrigin
    @PutMapping("/{EID}")
    public ResponseEntity<String> update(@RequestBody EmployeeLeaveInfo leaveInfo, @PathVariable Integer EID) {
        if (employeeLeaveInfoService.validate(leaveInfo)) {

            boolean exists = employeeLeaveInfoService.eidExistsInTable(EID);
            leaveInfo.setEID(EID);
            employeeLeaveInfoService.add(leaveInfo);
            return exists ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                          : ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @CrossOrigin
    @DeleteMapping("/{EID}")
    public ResponseEntity<EmployeeLeaveInfo> delete(@PathVariable Integer EID) {
        if (employeeLeaveInfoService.eidExistsInTable(EID)) {
            employeeLeaveInfoService.delete(EID);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
