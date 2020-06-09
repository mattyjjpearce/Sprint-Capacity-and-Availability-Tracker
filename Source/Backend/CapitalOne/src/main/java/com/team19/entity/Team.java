package com.team19.entity;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @Column(name = "TeamID")
    private Integer teamId;

    @Column(name = "TeamName")
    private String teamName;

    @OneToOne(targetEntity = com.team19.entity.Employee.class)
    @JoinColumn(name="TeamManagerID", referencedColumnName="EID", nullable = false)
    private Employee teamManager;

    public Team(Integer teamId, String teamName, Integer teamManagerId) {
        teamManager = new Employee();
        this.setTeamId(teamId);
        this.setTeamName(teamName);
        this.setTeamManagerId(teamManagerId);
    }

    public Team() { teamManager = new Employee(); }

    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    public Integer getTeamId() { return teamId; }

    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getTeamName() { return teamName; }

    public void setTeamManagerId(Integer teamManagerId) { this.teamManager.setEid(teamManagerId); }
    public Integer getTeamManagerId() { return teamManager.getEid(); }

    public Employee getTeamManager() { return teamManager; }
    public void setTeamManager(Employee employee) {
        this.teamManager = employee;
    }

    @Override
    public boolean equals(Object obj) {
        Team other = (Team)obj;
        return this.teamId.equals(other.teamId) &&
                this.teamName.equals(other.teamName) &&
                this.teamManager.getEid().equals(other.teamManager.getEid());
    }

    public static class Builder {
        private Integer teamId;
        private String teamName;
        private Integer teamManagerId;

        public Builder(Integer teamId) {
            this.teamId = teamId;
        }

        public Team build() {
            return new Team(teamId, teamName, teamManagerId);
        }

        public Builder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public Builder withTeamManagerId(Integer teamManagerId) {
            this.teamManagerId = teamManagerId;
            return this;
        }
    }
}
