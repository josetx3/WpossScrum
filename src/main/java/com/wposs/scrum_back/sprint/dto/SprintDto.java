package com.wposs.scrum_back.sprint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class SprintDto {
    @JsonProperty(value = "sprintId",access = JsonProperty.Access.READ_ONLY)
    private UUID sprintId;
    @JsonProperty(value = "areaId")
    @NotNull
    private UUID areaId;
    @JsonProperty(value = "teamId")
    @NotNull
    private UUID teamId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date sprintStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date sprintEnd;

    @JsonProperty(value = "sprintDay")
    @NotNull
    private String sprintDay;
    @JsonProperty(value = "nuSprint",access = JsonProperty.Access.READ_ONLY)
    private Integer sprintCount;
    @JsonProperty(value = "teamName",access = JsonProperty.Access.READ_ONLY)
    private String teamName;
    @JsonProperty(value = "sprintDaysDate")
    private Double sprintDaysDate;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
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

    public Date getSprintStart() {
        return sprintStart;
    }

    public void setSprintStart(Date sprintStart) {
        this.sprintStart = sprintStart;
    }

    public Date getSprintEnd() {
        return sprintEnd;
    }

    public void setSprintEnd(Date sprintEnd) {
        this.sprintEnd = sprintEnd;
    }

    public Integer getSprintCount() {
        return sprintCount;
    }

    public void setSprintCount(Integer sprintCount) {
        this.sprintCount = sprintCount;
    }

    public String getSprintDay() {
        return sprintDay;
    }

    public void setSprintDay(String sprintDay) {
        this.sprintDay = sprintDay;
    }

    public Double getSprintDaysDate() {
        return sprintDaysDate;
    }

    public void setSprintDaysDate(Double sprintDaysDate) {
        this.sprintDaysDate = sprintDaysDate;
    }
}
