package com.wposs.scrum_back.scoresprintdays.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoringSprintDaysDto {

    @JsonProperty(value = "scoreSprintId",access = JsonProperty.Access.READ_ONLY)
    private UUID scoreSprintId;

    @JsonProperty(value = "scoreSprint")
    private Double scoreSprint;

    @JsonProperty(value = "sprintId")
    @NotNull
    private UUID sprintId;

    public UUID getScoreSprintId() {
        return scoreSprintId;
    }

    public void setScoreSprintId(UUID scoreSprintId) {
        this.scoreSprintId = scoreSprintId;
    }

    public Double getScoreSprint() {
        return scoreSprint;
    }

    public void setScoreSprint(Double scoreSprint) {
        this.scoreSprint = scoreSprint;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
    }
}
