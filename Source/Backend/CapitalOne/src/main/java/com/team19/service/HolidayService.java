package com.team19.service;

import com.team19.entity.Employee;
import com.team19.entity.Holiday;
import com.team19.repository.EmployeeRepository;
import com.team19.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Transactional
public class HolidayService {

    private HolidayRepository holidayRepository;

    public HolidayService(@Autowired HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public Holiday add(Holiday holiday) {
        Integer holidayId = holiday.getHolidayId();
        Date startDate = holiday.getStartDate();
        Integer length = holiday.getLength();
        Integer employeeId = holiday.getEmployeeID();

        holiday.setHolidayId(holidayId);
        holiday.setStartDate(startDate);
        holiday.setLength(length);
        holiday.setEmployeeID(employeeId);
        holidayRepository.save(holiday);
        return holiday;
    }

    public List<Holiday> findAllBy(
            Integer employeeID,
            Date StartDate,
            Integer Length
    ) {
        Holiday e = new Holiday(
            employeeID,
            StartDate,
            Length
        );
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("employeeID", exact())
                .withMatcher("StartDate", exact())
                .withMatcher("Length", exact());
                //.withIgnorePaths("holidayId");
        Example<Holiday> example = Example.of(e, matcher);
        return holidayRepository.findAll(example);
    }

    public Holiday findById (Integer employeeId) {
        return holidayRepository.findById(employeeId).orElse(null);
    }

    public boolean validate (Holiday holiday) {
        Date startDate = holiday.getStartDate();
        Integer employeeID = holiday.getEmployeeID();
        List<Object> fields = Arrays.asList(startDate, employeeID);

        boolean containsNull = fields.stream().anyMatch(f -> f == null || f.toString().equals(""));

        return !containsNull && employeeID >= 0;
    }

    public boolean holidayIDExistsInTable(Integer HolidayID) {
        return holidayRepository.findById(HolidayID).orElse(null) != null;
    }

    public boolean employeeIDExistsInTable(Integer employeeID) {
        return holidayRepository.findById(employeeID).orElse(null) != null;
    }

    public Holiday delete(int HolidayID) {
        Holiday target = holidayRepository.findById(HolidayID).orElse(null);
        holidayRepository.deleteById(HolidayID);
        return target;
    }

}
