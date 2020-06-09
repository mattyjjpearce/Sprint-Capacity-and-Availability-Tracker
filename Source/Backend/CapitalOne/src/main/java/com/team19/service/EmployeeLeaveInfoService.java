package com.team19.service;

import com.team19.entity.EmployeeLeaveInfo;
import com.team19.repository.EmployeeLeaveInfoRepository;
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
public class EmployeeLeaveInfoService {

    private EmployeeLeaveInfoRepository employeeLeaveInfoRepository;

    public EmployeeLeaveInfoService(@Autowired EmployeeLeaveInfoRepository employeeLeaveInfoRepository) {
        this.employeeLeaveInfoRepository = employeeLeaveInfoRepository;
    }

    public void add(EmployeeLeaveInfo employeeLeaveInfo) {
        employeeLeaveInfoRepository.save(employeeLeaveInfo);
    }

    public List<EmployeeLeaveInfo> findAllBy(Integer EID) {
        EmployeeLeaveInfo e = new EmployeeLeaveInfo(EID);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("EID", exact());

        Example<EmployeeLeaveInfo> example = Example.of(e, matcher);
        return employeeLeaveInfoRepository.findAll(example);
    }

    public boolean validate(EmployeeLeaveInfo employeeLeaveInfo) {
        Integer eid = employeeLeaveInfo.getEID();
        Integer totalDaysLeft = employeeLeaveInfo.getTotalDaysLeft();
        Integer daysBooked = employeeLeaveInfo.getDaysBooked();
        Integer entitlement = employeeLeaveInfo.getEntitlement();
        Integer holidaysPurchased = employeeLeaveInfo.getHolidaysPurchased();
        Integer holidaysSold = employeeLeaveInfo.getHolidaysSold();
        Integer daysCarriedFromLastYear = employeeLeaveInfo.getDaysCarriedFromLastYr();
        Integer daysCarriedForwardToNextYear = employeeLeaveInfo.getDaysCarriedFwrdNextYr();
        Integer familyDays = employeeLeaveInfo.getFamilyDays();
        Integer emergencyDays = employeeLeaveInfo.getEmergencyDays();
        Integer sickDays = employeeLeaveInfo.getEmergencyDays();
        Integer parentalLeaveDays = employeeLeaveInfo.getParentalLeaveDays();

        List<Object> fields = Arrays.asList(eid, totalDaysLeft,
                daysBooked, entitlement, holidaysPurchased,
                holidaysSold, daysCarriedFromLastYear,
                daysCarriedForwardToNextYear, familyDays, emergencyDays,
                sickDays, parentalLeaveDays);

        boolean containsNull = fields.stream().anyMatch(f -> f == null || f.toString().equals(""));

        return !containsNull;
    }

    public boolean eidExistsInTable(Integer employeeId) {
        return employeeLeaveInfoRepository.findById(employeeId).orElse(null) != null;
    }

    public void delete(Integer employeeId) {
        employeeLeaveInfoRepository.deleteById(employeeId);
    }

}