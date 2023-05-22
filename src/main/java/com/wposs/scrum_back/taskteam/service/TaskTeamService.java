package com.wposs.scrum_back.taskteam.service;


import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskTeamService {
    List<TaskTeamDto> getAllTaskTeam();
    List<TaskTeamDto> getTaskTeamToIdTeam(UUID idTeam);
    Optional<TaskTeamDto> getTaskTeamById(UUID idTaskTeam);
    TaskTeamDto saveTaskTeam(TaskTeamDto taskTeamDto);
    TaskTeamDto updateTaskTeam(UUID idTasTeam,TaskTeamDto taskTeamDto);
    Boolean deleteTaskTeam(UUID idTasTeam);
}
