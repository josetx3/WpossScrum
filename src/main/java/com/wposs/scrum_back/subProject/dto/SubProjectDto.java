package com.wposs.scrum_back.subProject.dto;

import  com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubProjectDto {

    @JsonProperty(value = "subProjectId", access = JsonProperty.Access.READ_ONLY)
    private UUID subProjectId;

    @JsonProperty(value = "subProjectName")
    @NotNull(message = "El campo del SubProject no puede ser null")
    @NotEmpty
    @Size(max = 100,message = "El nombre del SubProject no puede sobre pasar los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "En el nombre del subprojecto solo se aceptan letras")
    private String subProjectName;

    @JsonProperty(value = "projectId")
    @NotNull(message = "El campo del proyecto no debe ser null")
    private UUID projectId;

    @JsonProperty(value = "teamId")
    @NotNull
    private UUID teamId;
    @JsonProperty(value = "projectName",access = JsonProperty.Access.READ_ONLY)
    private String projectName;
    @JsonProperty(value = "teamName",access = JsonProperty.Access.READ_ONLY)
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UUID getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(UUID subProjectId) {
        this.subProjectId = subProjectId;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }
}
