package com.wposs.scrum_back.project.service;

import com.wposs.scrum_back.project.dto.ProjectDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {
    List<ProjectDto> gatAllProject();
    Optional<ProjectDto> getProjectId(UUID idProject);
    ProjectDto saveProject(ProjectDto projectDto);
    ProjectDto updateProject(UUID idProject,ProjectDto projectDto);
    List<ProjectDto> getProjectToArea(UUID idArea);
}
