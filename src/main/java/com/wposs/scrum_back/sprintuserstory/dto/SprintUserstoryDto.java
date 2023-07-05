package com.wposs.scrum_back.sprintuserstory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class SprintUserstoryDto implements Serializable {

    @JsonProperty(value = "points")
    @DecimalMin(value = "1")
    private Integer points;

    @JsonProperty(value = "idSprint")
    @NotNull
    private UUID idSprint;

    @JsonProperty(value = "userStoryId")
    @NotNull
    private UUID userStoryId;

    @JsonProperty(value = "sprintUserstoryDate")
    @NotNull
    private Integer sprintUserstoryDate;


    public SprintUserstoryDto(Integer points, UUID idSprint, UUID userStoryId, Integer sprintUserstoryDate){
        this.points= points;
        this.idSprint= idSprint;
        this.userStoryId= userStoryId;
        this.sprintUserstoryDate= sprintUserstoryDate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UUID getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(UUID idSprint) {
        this.idSprint = idSprint;
    }

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }

    public Integer getSprintUserstoryDate() {
        return sprintUserstoryDate;
    }

    public void setSprintUserstoryDate(Integer sprintUserstoryDate) {
        this.sprintUserstoryDate = sprintUserstoryDate;
    }
}
