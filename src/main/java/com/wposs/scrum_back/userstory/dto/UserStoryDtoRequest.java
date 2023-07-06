package com.wposs.scrum_back.userstory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class UserStoryDtoRequest {

    @JsonProperty(value = "userStoryName")
    @NotEmpty
    private String userStoryName;

    @NotNull(message = "historia de usuario no debe ser null")
    private Integer userStoryScore;

    @JsonProperty(value = "userStoryStateName",access = JsonProperty.Access.READ_ONLY)
    private String userStoryStateName;

    @JsonProperty(value = "userstoryId")
    private UUID userstoryId;
    @JsonProperty(value = "subProjectName")
    private String subProjectName;

    @JsonProperty(value = "projectName")
    private String projectName;

    @JsonProperty(value = "areaName")
    @NotNull
    private String areaName;

    @JsonProperty(value = "teamName")
    private String teamName;


    public UserStoryDtoRequest(String userStoryName, Integer userStoryScore, String userStoryStateName, String subProjectName, String projectName, String areaName, String teamName, UUID userstoryId) {
        this.userStoryName = userStoryName;
        this.userStoryScore = userStoryScore;
        this.userStoryStateName = userStoryStateName;
        this.subProjectName = subProjectName;
        this.projectName = projectName;
        this.areaName = areaName;
        this.teamName = teamName;
        this.userstoryId=userstoryId;
    }

    public String getUserStoryName() {
        return userStoryName;
    }

    public void setUserStoryName(String userStoryName) {
        this.userStoryName = userStoryName;
    }

    public Integer getUserStoryScore() {
        return userStoryScore;
    }

    public void setUserStoryScore(Integer userStoryScore) {
        this.userStoryScore = userStoryScore;
    }

    public String getUserStoryStateName() {
        return userStoryStateName;
    }

    public void setUserStoryStateName(String userStoryStateName) {
        this.userStoryStateName = userStoryStateName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
