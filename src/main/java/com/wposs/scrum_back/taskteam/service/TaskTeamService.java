package com.wposs.scrum_back.taskteam.service;


import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskTeamService {
    List<TaskTeamDto> getAllTaskTeam();
    List<TaskTeamDto> getTaskTeamToIdTeam(UUID idTeam);
    List<TaskTeamDto> getTaskTeamToIdTeamAndIdUserStory(UUID teamId, UUID userStoryId);
    List<TaskTeamDtoRequest> getTaskTeamToIdTeamAndUserStory(UUID teamId, UUID userStoryId);
    Optional<TaskTeamDto> getTaskTeamById(UUID idTaskTeam);
    TaskTeamDto saveTaskTeam(TaskTeamDto taskTeamDto);
    TaskTeamDto updateTaskTeam(UUID idTasTeam,TaskTeamDto taskTeamDto);
    TaskTeamDto updateTaskTeamState(UUID idTasTeam,UUID idBoard, TaskTeamDto taskTeamDto);
    Boolean deleteTaskTeam(UUID idTasTeam);
}
