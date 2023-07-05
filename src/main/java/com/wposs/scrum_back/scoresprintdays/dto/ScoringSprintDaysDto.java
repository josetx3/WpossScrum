package com.wposs.scrum_back.scoresprintdays.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoringSprintDaysDto {

    @JsonProperty(value = "scoreSprintId",access = JsonProperty.Access.READ_ONLY)
    private UUID scoreSprintId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @JsonProperty(value = "scoreSprint")
    private Double scoreSprint;

    @JsonProperty(value = "idSprint")
    @NotNull
    private UUID idSprint;

    public UUID getScoreSprintId() {
        return scoreSprintId;
    }

    public void setScoreSprintId(UUID scoreSprintId) {
        this.scoreSprintId = scoreSprintId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getScoreSprint() {
        return scoreSprint;
    }

    public void setScoreSprint(Double scoreSprint) {
        this.scoreSprint = scoreSprint;
    }

    public UUID getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(UUID idSprint) {
        this.idSprint = idSprint;
    }
}
