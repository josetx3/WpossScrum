package com.wposs.scrum_back.subProject.service;

import com.wposs.scrum_back.subProject.dto.SubProjectDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubProjectService {
    List<SubProjectDto> gatAllSubProject();
    List<SubProjectDto> getSubProjectToProject(UUID idProject);
    List<SubProjectDto> getSubprojectsToClient(String clientId);
    Optional<SubProjectDto> gatSubProjectId(UUID idSubProject);
    SubProjectDto saveSubProject(SubProjectDto subProjectDto);
    SubProjectDto updateSubProject(UUID idSubProject,SubProjectDto subProjectDto);

}
