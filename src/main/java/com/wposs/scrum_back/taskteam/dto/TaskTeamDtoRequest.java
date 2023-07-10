package com.wposs.scrum_back.taskteam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TaskTeamDtoRequest {

    @JsonProperty(value="taskTeamId")
    private UUID taskTeamId;

    @JsonProperty(value = "taskName")
    private String taskName;

    @JsonProperty(value = "taskHours")
    private Integer taskHours;

    @JsonProperty(value = "sprintNumber")
    private Integer sprintNumber;

    public TaskTeamDtoRequest(UUID taskTeamId, String taskName, Integer taskHours, Integer sprintNumber) {
        this.taskTeamId = taskTeamId;
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.sprintNumber = sprintNumber;
    }

    public UUID getTaskTeamId() {
        return taskTeamId;
    }

    public void setTaskTeamId(UUID taskTeamId) {
        this.taskTeamId = taskTeamId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(Integer taskHours) {
        this.taskHours = taskHours;
    }

    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }
}
