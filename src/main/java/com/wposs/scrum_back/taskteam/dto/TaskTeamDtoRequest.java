package com.wposs.scrum_back.taskteam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskTeamDtoRequest {

    @JsonProperty(value = "taskName")
    private String taskName;

    @JsonProperty(value = "sprintNumber")
    private Integer sprintNumber;

    public TaskTeamDtoRequest(String taskName, Integer sprintNumber) {
        this.taskName = taskName;
        this.sprintNumber = sprintNumber;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }
}
