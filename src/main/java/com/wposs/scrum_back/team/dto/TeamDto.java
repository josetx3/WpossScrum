package com.wposs.scrum_back.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

public class TeamDto implements Serializable {
    @JsonProperty(value = "teamId",access = JsonProperty.Access.READ_ONLY)
    private UUID teamId;
    @JsonProperty(value = "teamName")
    @NotBlank(message = "El nombre del Equipo no debe ser null")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "El nombre del Equipo solo debe contener lestras y espacios")
    @Size(max = 100,message = "El nombre del Equipo no debe sobre pasar los 100 caractares")
    private String teamName;
    @JsonProperty(value = "areaId")
    private UUID areaId;
    @JsonProperty(value = "areaName",access = JsonProperty.Access.READ_ONLY)
    private String areaName;

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
}
