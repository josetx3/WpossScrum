package com.wposs.scrum_back.team.service;

import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.team.dto.TeamDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {
    List<TeamDto> getAllTeam();
    List<TeamDto> getTeamToArea(UUID idArea);
    Optional<TeamDto> getTeamByiId(UUID idTeam);
    TeamDto saveTeam(TeamDto teamDto);
    TeamDto updateTeam(UUID idTeam,TeamDto teamDto);
    TeamDto saveEmployeToTeam(List<UUID> employeId,UUID idTeam);
}
