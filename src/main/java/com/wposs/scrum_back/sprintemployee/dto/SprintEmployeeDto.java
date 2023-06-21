package com.wposs.scrum_back.sprintemployee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class SprintEmployeeDto implements Serializable {
//    @JsonProperty(value = "idEmployee")
//    @NotNull
//    private UUID idEmployee;
//    @JsonProperty(value = "idSprint")
//    @NotNull
//    private UUID idSprint;

    @JsonProperty(value = "employeeName")
    private String employeeName;
    @JsonProperty(value = "daysLeave")
    @DecimalMin(value = "1")
    private Integer daysLeave;
    @JsonProperty(value = "observations")
    private String observations;
    @JsonProperty(value = "percentage")
    @DecimalMin(value = "0.01")
    private Double percentage;
    @JsonProperty(value = "percentageFinal")
    private Double percentageFinal;


    public SprintEmployeeDto(String employeeName, Integer daysLeave, String observations, Double percentage, Double percentageFinal) {
        this.employeeName = employeeName;
        this.daysLeave = daysLeave;
        this.observations = observations;
        this.percentage = percentage;
        this.percentageFinal = percentageFinal;
    }

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

    public Double getPercentageFinal() {
        return percentageFinal;
    }

    public void setPercentageFinal(Double percentageFinal) {
        this.percentageFinal = percentageFinal;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
