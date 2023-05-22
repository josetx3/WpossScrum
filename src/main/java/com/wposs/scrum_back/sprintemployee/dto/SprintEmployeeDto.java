package com.wposs.scrum_back.sprintemployee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class SprintEmployeeDto implements Serializable {
    @JsonProperty(value = "idEmployee")
    @NotNull
    private UUID idEmployee;
    @JsonProperty(value = "idSprint")
    @NotNull
    private UUID idSprint;
    @JsonProperty(value = "percentage")
    @DecimalMin(value = "0.01")
    private Double percentage;
    @JsonProperty(value = "daysLeave")
    @DecimalMin(value = "1")
    private Integer daysLeave;
    @JsonProperty(value = "observations")
    private String observations;

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getDaysLeave() {
        return daysLeave;
    }

    public void setDaysLeave(Integer daysLeave) {
        this.daysLeave = daysLeave;
    }

    public UUID getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(UUID idEmployee) {
        this.idEmployee = idEmployee;
    }

    public UUID getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(UUID idSprint) {
        this.idSprint = idSprint;
    }
}
