package com.wposs.scrum_back.sprintuserstory.service;

import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDtoRequest;

import java.util.List;
import java.util.UUID;

public interface SprintUserstoryService {

    SprintUserstoryDto saveSprintUserStory(SprintUserstoryDto sprintUserstoryDto);


    List<SprintUserstoryDtoRequest> getAllSprintUserstoryBySprint(UUID sprintId);
}
