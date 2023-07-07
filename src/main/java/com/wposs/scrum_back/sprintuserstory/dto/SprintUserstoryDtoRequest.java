package com.wposs.scrum_back.sprintuserstory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SprintUserstoryDtoRequest {

    @JsonProperty(value = "userStoryName")
    private String userStoryName;

    @JsonProperty(value = "projectName")
    private String projectName;

    @JsonProperty(value = "subProjectName")
    private String subProjectName;

    @JsonProperty(value = "points")
    private Integer points;

    @JsonProperty(value = "sprintId")
    private UUID sprintId;

    @JsonProperty(value = "userstoryId")
    private UUID userstoryId;

    public SprintUserstoryDtoRequest(String userStoryName, String projectName, String subProjectName, Integer points, UUID sprintId, UUID userstoryId) {
        this.userStoryName = userStoryName;
        this.projectName = projectName;
        this.subProjectName = subProjectName;
        this.points = points;
        this.sprintId = sprintId;
        this.userstoryId = userstoryId;
    }

    public String getUserStoryName() {
        return userStoryName;
    }

    public void setUserStoryName(String userStoryName) {
        this.userStoryName = userStoryName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
    }

    public UUID getUserstoryId() {
        return userstoryId;
    }

    public void setUserstoryId(UUID userstoryId) {
        this.userstoryId = userstoryId;
    }

    //us.user_story_name, pr.project_name, sp.sub_project_name, sus.points
}
