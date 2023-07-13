package com.wposs.scrum_back.project.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.project.dto.ProjectDto;
import com.wposs.scrum_back.project.entity.Project;
import com.wposs.scrum_back.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProjectDto> gatAllProject() {
        return projectRepository.findAll().stream().map(project -> {
            return modelMapper.map(project,ProjectDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectDto> getProjectId(UUID idProject) {
        return Optional.ofNullable(projectRepository.findById(idProject).map(project -> {
            return modelMapper.map(project,ProjectDto.class);
        }).orElseThrow(()->new MessageGeneric("EL Projecto solicitado no se encuentra Registrado","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ProjectDto saveProject(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto,Project.class);
        if (projectRepository.existsByProjectNameAndAreaId(project.getProjectName(),project.getAreaId())){
            throw new MessageGeneric("Ya existe un Proyecto con este nombre: "+project.getProjectName()+" Asociado al Area Selecionada","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(projectRepository.save(project),ProjectDto.class);
        }catch (Exception ex){
            throw new InternalServerException("Error al Intentar Crear el proyecto,JSON mal estructurado","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProjectDto updateProject(UUID idProject, ProjectDto projectDto) {
        return projectRepository.findById(idProject).map(project -> {
            project.setProjectName((projectDto.getProjectName()!=null)?projectDto.getProjectName():project.getProjectName());
            project.setAreaId((projectDto.getAreaId()!=null)?projectDto.getAreaId():project.getAreaId());
            project.setClientId(projectDto.getClientId()!=null?projectDto.getClientId():project.getClientId());
            return modelMapper.map(projectRepository.save(project),ProjectDto.class);
        }).orElseThrow(()->new RequestException("Error, no se pudo actualizar el Proyecto","400", HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<ProjectDto> getProjectToArea(UUID idArea) {
        return projectRepository.getByAreaId(idArea).stream().map(project -> {
            return modelMapper.map(project,ProjectDto.class);
        }).collect(Collectors.toList());
    }
}
