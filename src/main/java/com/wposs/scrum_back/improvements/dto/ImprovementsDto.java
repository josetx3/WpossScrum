package com.wposs.scrum_back.improvements.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class ImprovementsDto implements Serializable {
    @JsonProperty(value = "improvementsId", access = JsonProperty.Access.READ_ONLY)
    private UUID improvementsId;
    @JsonProperty(value = "areaId")
    @NotNull
    private UUID areaId;
    @JsonProperty(value = "teamId")
    @NotNull
    private UUID teamId;
    @JsonProperty(value = "userStoryId")
    @NotNull
    private UUID userStoryId;
    @JsonProperty(value = "taskId")
    @NotNull
    private UUID taskId;
    @JsonProperty(value = "observationId")
    @NotNull
    private UUID observationId;
    @JsonProperty(value = "observationn")
    @NotBlank
    private String observationn;

    @JsonProperty(value = "areaName",access = JsonProperty.Access.READ_ONLY)
    private String areaName;
    @JsonProperty(value = "teamName",access = JsonProperty.Access.READ_ONLY)
    private String teamName;
    @JsonProperty(value = "userStoryName",access = JsonProperty.Access.READ_ONLY)
    private String userStoryName;
    @JsonProperty(value = "nameTask",access = JsonProperty.Access.READ_ONLY)
    private String nameTask;
    @JsonProperty(value = "observationName",access = JsonProperty.Access.READ_ONLY)
    private String observationName;

    public UUID getImprovementsId() {
        return improvementsId;
    }

    public void setImprovementsId(UUID improvementsId) {
        this.improvementsId = improvementsId;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getObservationId() {
        return observationId;
    }

    public void setObservationId(UUID observationId) {
        this.observationId = observationId;
    }

    public String getObservationn() {
        return observationn;
    }

    public void setObservationn(String observationn) {
        this.observationn = observationn;
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

    public String getUserStoryName() {
        return userStoryName;
    }

    public void setUserStoryName(String userStoryName) {
        this.userStoryName = userStoryName;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getObservationName() {
        return observationName;
    }

    public void setObservationName(String observationName) {
        this.observationName = observationName;
    }

}
