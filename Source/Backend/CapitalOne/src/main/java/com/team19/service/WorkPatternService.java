package com.team19.service;

import com.team19.entity.WorkPattern;
import com.team19.repository.WorkPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Transactional
public class WorkPatternService {

    private WorkPatternRepository repository;

    public WorkPatternService(@Autowired WorkPatternRepository repository) {
        this.repository = repository;
    }

    public WorkPattern add(WorkPattern pattern)
    {
        Integer workPatternId = pattern.getWorkPatternId();
        Integer employeeId = pattern.getEid();
        Integer mondayHours = pattern.getMondayHours();
        Integer tuesdayHours = pattern.getTuesdayHours();
        Integer wednesdayHours = pattern.getWednesdayHours();
        Integer thursdayHours = pattern.getThursdayHours();
        Integer fridayHours = pattern.getFridayHours();

        pattern.setWorkPatternId(workPatternId);
        pattern.setEid(employeeId);
        pattern.setMondayHours(mondayHours);
        pattern.setTuesdayHours(tuesdayHours);
        pattern.setWednesdayHours(wednesdayHours);
        pattern.setThursdayHours(thursdayHours);
        pattern.setFridayHours(fridayHours);
        repository.save(pattern);
        return pattern;
    }


    public List<WorkPattern> findAllBy(
            Integer EID,
            Integer mondayHours,
            Integer tuesdayHours,
            Integer wednesdayHours,
            Integer thursdayHours,
            Integer fridayHours
    ) {
       WorkPattern e = new WorkPattern(
               EID,
               mondayHours,
               tuesdayHours,
               wednesdayHours,
               thursdayHours,
               fridayHours
       );
       ExampleMatcher matcher = ExampleMatcher.matching()
               .withIgnoreNullValues()
               .withIgnoreCase()
               .withMatcher("EID", exact())
               .withMatcher("mondayHours", exact())
               .withMatcher("tuesdayHours", exact())
               .withMatcher("wednesdayHours", exact())
               .withMatcher("thursdayHours", exact())
               .withMatcher("fridayHours", exact());

       Example<WorkPattern> example = Example.of(e, matcher);
       return repository.findAll(example);
    }

    public WorkPattern findById (Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Boolean validate (WorkPattern pattern) {
        Integer eid = pattern.getEid();
        Integer mondayHours = pattern.getMondayHours();
        Integer tuesdayHours = pattern.getTuesdayHours();
        Integer wednesdayHours = pattern.getWednesdayHours();
        Integer thursdayHours = pattern.getThursdayHours();
        Integer fridayHours = pattern.getFridayHours();
        List<Object> fields = Arrays.asList(eid, mondayHours, tuesdayHours,
                wednesdayHours, thursdayHours, fridayHours);

        boolean containsNull = fields.stream().anyMatch(f -> f == null || f.toString().equals(""));
        return !containsNull;
    }


    public boolean employeeIDExistsInTable(Integer eID)
    {
        return repository.findById(eID).orElse(null) != null;
    }

    public boolean workPatternIdExistsInTable (Integer workPatternId) {
        return repository.findById(workPatternId).orElse(null) != null;
    }

    public WorkPattern update (Integer workPatternId, WorkPattern workPattern) {
        WorkPattern dest = this.findById(workPatternId);

        System.out.println(workPatternId);

        Integer mondayHours = workPattern.getMondayHours() == null
                ? dest.getMondayHours()
                : workPattern.getMondayHours();

        Integer tuesdayHours = workPattern.getTuesdayHours() == null
                ? dest.getTuesdayHours()
                : workPattern.getTuesdayHours();

        Integer wednesdayHours = workPattern.getWednesdayHours() == null
                ? dest.getWednesdayHours()
                : workPattern.getWednesdayHours();

        Integer thursdayHours = workPattern.getThursdayHours() == null
                ? dest.getThursdayHours()
                : workPattern.getThursdayHours();

        Integer fridayHours = workPattern.getFridayHours() == null
                ? dest.getFridayHours()
                : workPattern.getFridayHours();

        Integer employeeId = workPattern.getEid() == null
                ? dest.getEid()
                : workPattern.getEid();

        Integer totalHours = mondayHours + tuesdayHours + wednesdayHours + thursdayHours
                + fridayHours;

        if (totalHours < 36.5 || employeeId < 0) {
            return null;
        }

        workPattern.setWorkPatternId(workPatternId);
        workPattern.setMondayHours(mondayHours);
        workPattern.setTuesdayHours(tuesdayHours);
        workPattern.setWednesdayHours(wednesdayHours);
        workPattern.setThursdayHours(thursdayHours);
        workPattern.setFridayHours(fridayHours);
        workPattern.setEid(employeeId);

        repository.save(workPattern);
        return workPattern;
    }

    public WorkPattern delete(int eID)
    {
        WorkPattern target = repository.findById(eID).orElse(null);
        repository.deleteById(eID);
        return target;
    }

}
