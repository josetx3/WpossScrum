package com.wposs.scrum_back.team.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wposs.scrum_back.employe.dto.EmployeDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamEmployeDto {

    @JsonProperty(value ="teamId")
    private UUID teamId;
    @JsonProperty(value = "employeesid")
    private List<UUID> idEmployees;

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public List<UUID> getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(List<UUID> idEmployees) {
        this.idEmployees = idEmployees;
    }
}
