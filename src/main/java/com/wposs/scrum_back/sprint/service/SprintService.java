package com.wposs.scrum_back.sprint.service;

import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprint.dto.SprintDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SprintService {
    List<SprintDto> getAllSprint();
    SprintDto saveSprint(SprintDto sprintDto);
    Optional<SprintDto> sprintById(UUID sprintId);
    SprintDto updateSprint(UUID sprintId,SprintDto sprintDto);

    SprintDtoRequest getDataSprint(UUID idSprint);

    List<SprintDto> getSprintByTeam(UUID teamId);
}
