package com.team19.service;

import com.team19.entity.Team;
import com.team19.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository repository;

    public List<Team> findAllBy(Integer teamID, String teamName, Integer teamManagerId) {
        Team team = new Team.Builder(teamID)
                .withTeamName(teamName)
                .withTeamManagerId(teamManagerId)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("teamId", exact())
                .withMatcher("teamName", exact())
                .withMatcher("teamManagerId", exact());
        Example<Team> example = Example.of(team, matcher);
        return repository.findAll(example);
    }

    public boolean eidExistsInTable(Integer teamID) {
        return repository.findById(teamID).orElse(null) != null;
    }

    public Team findById(Integer teamID) {
        return repository.findById(teamID).orElse(null);
    }

    public boolean validate(Team team) {
        Integer teamId = team.getTeamId();
        String teamName = team.getTeamName();
        Integer teamManagerId = team.getTeamManagerId();

        return teamId != null &&
                teamName != null &&
                teamManagerId != null &&
                teamId >= 0 &&
                !teamName.equals("") &&
                teamManagerId >= 0;
    }

    public void add(Team team) {
        String teamName = team.getTeamName().trim();
        team.setTeamName(teamName);
        repository.save(team);
    }

    public Team put(Integer teamId, Team team) {
        Team dest = this.findById(teamId);

        String teamName = team.getTeamName() == null
                ? dest.getTeamName()
                : team.getTeamName();
        teamName = teamName.trim();

        Integer teamManagerId = team.getTeamManagerId() == null
                ? dest.getTeamManagerId()
                : team.getTeamManagerId();

        // Sanity check
        if (teamName.equals("") || teamManagerId < 0) {
            return null;
        }

        team.setTeamId(teamId);
        team.setTeamName(teamName);
        team.setTeamManagerId(teamManagerId);

        repository.save(team);
        return team;
    }

    public Team delete(Integer teamId) {
        Team team = this.findById(teamId);
        if (team == null) {
            return null;
        }
        repository.delete(team);
        return team;
    }
}
