package com.team19.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SprintID")
    private Integer sprintId;

    @Column(name = "SprintDescription")
    private String sprintDescription;

    @OneToOne(targetEntity = com.team19.entity.Team.class)
    @JoinColumn(name = "TeamID", referencedColumnName = "TeamID", nullable = false)
    public Team team;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "SprintLength")
    private Integer sprintLength;

    @Column(name = "PointsPlanned")
    private Integer pointsPlanned;

    @Column(name = "PointsCompleted")
    private Integer pointsCompleted;


    public Sprint(String sprintDescription, Date startDate, Integer sprintLength,
                  Integer pointsPlanned, Integer pointsCompleted, Integer teamId) {
        setSprintDescription(sprintDescription);
        this.team = new Team();
        setStartDate(startDate);
        setSprintLength(sprintLength);
        setPointsPlanned(pointsPlanned);
        setPointsCompleted(pointsCompleted);
        setTeamId(teamId);
    }

    public Sprint() {
        this.team = new Team();
    }

    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    public String getSprintDescription() {
        return sprintDescription;
    }

    public void setSprintDescription(String sprintDescription) {
        this.sprintDescription = sprintDescription;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getTeamId() {
        return team.getTeamId();
    }

    public void setTeamId(Integer teamId) {
        this.team.setTeamId(teamId);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getSprintLength() {
        return sprintLength;
    }

    public void setSprintLength(Integer sprintLength) {
        this.sprintLength = sprintLength;
    }

    public Integer getPointsPlanned() {
        return pointsPlanned;
    }

    public void setPointsPlanned(Integer pointsPlanned) {
        this.pointsPlanned = pointsPlanned;
    }

    public Integer getPointsCompleted() {
        return pointsCompleted;
    }

    public void setPointsCompleted(Integer pointsCompleted) {
        this.pointsCompleted = pointsCompleted;
    }



    public static class Builder {
        private Integer sprintId;
        private String sprintDescription;
        private Integer teamId;
        private Date startDate;
        private Integer sprintLength;
        private Integer pointsPlanned;
        private Integer pointsCompleted;

        public Sprint build() {
            return new Sprint(
                    sprintDescription,
                    startDate,
                    sprintLength,
                    pointsPlanned,
                    pointsCompleted,
                    teamId
            );
        }

        public Builder (Integer sprintId) {
            this.sprintId = sprintId;
        }

        public Builder withSprintDescription(String sprintDescription) {
            this.sprintDescription = sprintDescription;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withSprintLength(Integer sprintLength) {
            this.sprintLength = sprintLength;
            return this;
        }

        public Builder withPointsPlanned(Integer pointsPlanned) {
            this.pointsPlanned = pointsPlanned;
            return this;
        }

        public Builder withPointsCompleted(Integer pointsCompleted) {
            this.pointsCompleted = pointsCompleted;
            return this;
        }

        public Builder withTeamId(Integer teamId) {
            this.teamId = teamId;
            return this;
        }

    }

}
