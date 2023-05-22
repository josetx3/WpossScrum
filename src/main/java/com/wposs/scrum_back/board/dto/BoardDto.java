package com.wposs.scrum_back.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class BoardDto implements Serializable {
    @JsonProperty(value = "idBoard",access = JsonProperty.Access.READ_ONLY)
    private UUID idBoard;
    @NotNull
    @JsonProperty(value = "teamId")
    private UUID teamId;
    @NotNull
    @JsonProperty(value = "userStoryId")
    private UUID userStoryId;
    @NotNull
    @JsonProperty(value = "taskTeamId")
    private UUID taskTeamId;
    @NotNull
    @JsonProperty(value = "employeeId")
    private UUID employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String employeeName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public UUID getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(UUID idBoard) {
        this.idBoard = idBoard;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public UUID getTaskTeamId() {
        return taskTeamId;
    }

    public void setTaskTeamId(UUID taskTeamId) {
        this.taskTeamId = taskTeamId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
