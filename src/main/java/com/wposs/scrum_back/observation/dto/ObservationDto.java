package com.wposs.scrum_back.observation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

public class ObservationDto implements Serializable {
    @JsonProperty(value = "observationId",access = JsonProperty.Access.READ_ONLY)
    private UUID observationId;
    @JsonProperty(value = "observationName")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String observationName;

    public UUID getObservationId() {
        return observationId;
    }

    public void setObservationId(UUID observationId) {
        this.observationId = observationId;
    }

    public String getObservationName() {
        return observationName;
    }

    public void setObservationName(String observationName) {
        this.observationName = observationName;
    }
}
