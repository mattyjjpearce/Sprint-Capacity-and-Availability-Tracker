package com.team19.service;

import com.team19.entity.Sprint;
import com.team19.repository.SprintRepository;
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
public class SprintService {

    private SprintRepository sprintRepository;

    public SprintService(@Autowired SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint add(Sprint sprint) {
        String sprintDescription = sprint.getSprintDescription().trim();
        Date startDate = sprint.getStartDate();
        Integer sprintLength = sprint.getSprintLength();
        Integer pointsPlanned = sprint.getPointsPlanned();
        Integer pointsCompleted = sprint.getPointsCompleted();

        sprint.setSprintDescription(sprintDescription);
        sprint.setStartDate(startDate);
        sprint.setSprintLength(sprintLength);
        sprint.setPointsPlanned(pointsPlanned);
        sprint.setPointsCompleted(pointsCompleted);
        sprintRepository.save(sprint);
        return sprint;
    }

    public List<Sprint> findAllBy (
            String sprintDescription,
            Integer teamId,
            Date startDate,
            Integer sprintLength,
            Integer pointsPlanned,
            Integer pointsCompleted
    ) {
        Sprint sprint = new Sprint(
                sprintDescription,
                startDate,
                sprintLength,
                pointsPlanned,
                pointsCompleted,
                teamId
        );
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("SprintDescription", exact())
                .withMatcher("StartDate", exact())
                .withMatcher("SprintLength", exact())
                .withMatcher("PointsPlanned", exact())
                .withMatcher("PointsCompleted", exact())
                .withMatcher("TeamID", exact());
        Example<Sprint> example = Example.of(sprint, matcher);
        return sprintRepository.findAll(example);
    }

    public boolean validate (Sprint sprint) {
        Date startDate = sprint.getStartDate();
        Integer sprintLength = sprint.getSprintLength();
        Integer teamId = sprint.getTeamId();
        List<Object> fieldsToCheck = Arrays.asList(startDate, sprintLength, teamId);

        boolean containsNull = fieldsToCheck.stream().anyMatch(f -> f == null || f.toString().equals(""));
        return !containsNull && teamId >= 0 && sprintLength > 0;
    }

    public boolean sprintIdExistsInTable (Integer sprintId) {
        return sprintRepository.findById(sprintId).orElse(null) != null;
    }

    public Sprint findById(Integer id) {
        return sprintRepository.findById(id).orElse(null);
    }

    public Sprint update (Integer sprintId, Sprint sprint) {
        Sprint dest = this.findById(sprintId);

        String sprintDescription = sprint.getSprintDescription() == null
                ? dest.getSprintDescription()
                : sprint.getSprintDescription();
        sprintDescription = sprintDescription.trim();

        Integer teamId = sprint.getTeamId() == null
                ? dest.getTeamId()
                : sprint.getTeamId();

        Date startDate = sprint.getStartDate() == null
                ? dest.getStartDate()
                : sprint.getStartDate();

        Integer sprintLength = sprint.getSprintLength() == null
                ? dest.getSprintLength()
                : sprint.getSprintLength();

        Integer pointsPlanned = sprint.getPointsPlanned() == null
                ? dest.getPointsPlanned()
                : sprint.getPointsPlanned();

        Integer pointsCompleted = sprint.getPointsCompleted() == null
                ? dest.getPointsCompleted()
                : sprint.getPointsCompleted();

        if (sprintDescription.equals("") || teamId < 0 || sprintLength < 0 ||
                pointsPlanned <= 0 || pointsCompleted <= 0) {
            return null;
        }

        sprint.setSprintId(sprintId);
        sprint.setSprintDescription(sprintDescription);
        sprint.setTeamId(teamId);
        sprint.setStartDate(startDate);
        sprint.setSprintLength(sprintLength);
        sprint.setPointsPlanned(pointsPlanned);
        sprint.setPointsCompleted(pointsCompleted);

        sprintRepository.save(sprint);
        return sprint;
    }

    public Sprint delete (Integer sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId).orElse(null);
        if (sprint == null) {
            return null;
        }
        sprintRepository.deleteById(sprintId);
        return sprint;
    }

}
