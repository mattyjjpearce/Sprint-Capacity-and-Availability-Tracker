package com.team19.controller;

import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import com.team19.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/holidays")
public class HolidayController {

    private HolidayService holidayService;

    public HolidayController(@Autowired HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Holiday> addHoliday(@RequestBody Holiday holiday) {

        if (holidayService.validate(holiday)) {
            System.out.println("Received: " + holiday);
            Holiday result = holidayService.add(holiday);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @GetMapping
    public @ResponseBody Iterable<Holiday> findAllBy(
        @RequestParam(required = false) Integer employeeID,
        @RequestParam(required = false) Date startDate,
        @RequestParam(required = false) Integer length
    ) {
        return holidayService.findAllBy(employeeID, startDate, length);
    }

    @CrossOrigin
    @GetMapping("/{employeeID}")
    public ResponseEntity<Iterable<Holiday>> getHolidayByEmployee(@PathVariable Integer employeeID) {
        List<Holiday> result = holidayService.findAllBy(employeeID, null, null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);

        }
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Holiday> put(@RequestBody Holiday holiday) {

        if (holidayService.validate(holiday)) {
            boolean exists = holidayService.holidayIDExistsInTable(holiday.getHolidayId());
            Holiday result = holidayService.add(holiday);
            return exists ? new ResponseEntity<>(result, HttpStatus.OK)
                          : new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{HolidayID}")
    public ResponseEntity<Holiday> delete(@PathVariable Integer HolidayID) {
        if (holidayService.holidayIDExistsInTable(HolidayID)) {
            Holiday result = holidayService.delete(HolidayID);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
