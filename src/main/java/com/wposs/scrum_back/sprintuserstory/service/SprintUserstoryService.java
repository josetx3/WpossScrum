package com.wposs.scrum_back.sprintuserstory.service;

import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDtoRequest;

import java.util.List;
import java.util.UUID;

public interface SprintUserstoryService {

    SprintUserstoryDto saveSprintUserStory(SprintUserstoryDto sprintUserstoryDto);

    SprintUserstoryDto updateUserstoryService(UUID idSprint,UUID idUserStory,SprintUserstoryDto sprintUserstoryDto);
    Boolean deleteSpringUserStory(UUID idSprint,UUID iDUserStory);
    List<SprintUserstoryDtoRequest> getAllSprintUserstoryBySprint(UUID sprintId);


}
