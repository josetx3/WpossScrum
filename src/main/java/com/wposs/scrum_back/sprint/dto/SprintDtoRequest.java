package com.wposs.scrum_back.sprint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SprintDtoRequest {
    @JsonProperty(value = "areaName")
    private String areaName;

    @JsonProperty(value = "teamName")
    private String teamName;

    @JsonProperty(value = "nuSprint")
    private Integer sprintCount;

    @JsonProperty(value = "ScorePointSprint")
    private double score;

    public SprintDtoRequest(String areaName, String teamName, Integer sprintCount, double score) {
        this.areaName = areaName;
        this.teamName = teamName;
        this.sprintCount = sprintCount;
        this.score = score;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getSprintCount() {
        return sprintCount;
    }

    public void setSprintCount(Integer sprintCount) {
        this.sprintCount = sprintCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
