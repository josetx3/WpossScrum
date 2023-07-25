package com.wposs.scrum_back.subProject.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.subProject.dto.SubProjectDto;
import com.wposs.scrum_back.subProject.entity.SubProject;
import com.wposs.scrum_back.subProject.repository.SubProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubProjectServiceImpl implements SubProjectService{
    @Autowired
    private SubProjectRepository subProjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubProjectDto> gatAllSubProject() {
        return subProjectRepository.findAll().stream().map(subProject -> {
            return modelMapper.map(subProject,SubProjectDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<SubProjectDto> getSubProjectToProject(UUID idProject) {
        return subProjectRepository.getByProjectId(idProject).stream().map(subProject -> {
            return modelMapper.map(subProject,SubProjectDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<SubProjectDto> getSubprojectsToClient(String clientId) {
        return subProjectRepository.findSubprojectsByClient(clientId).stream().map(subProject -> {
            return modelMapper.map(subProject,SubProjectDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<SubProjectDto> gatSubProjectId(UUID idSubProject) {
        return Optional.of(subProjectRepository.findById(idSubProject).map(subProject -> {
            return modelMapper.map(subProject,SubProjectDto.class);
        })).orElseThrow(()->new MessageGeneric("No se encuentra Disponible el SubProyecto Solicitado","404", HttpStatus.NOT_FOUND));
    }

    @Override
    public SubProjectDto saveSubProject(SubProjectDto subProjectDto) {
        SubProject subProject = modelMapper.map(subProjectDto,SubProject.class);
        if(subProjectRepository.existsBySubProjectName(subProject.getSubProjectName())){
            throw new MessageGeneric("Ya existe este SubProjecto: "+subProject.getSubProjectName()+" Registrado","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(subProjectRepository.save(subProject),SubProjectDto.class);
        }catch (Exception ex){
            throw new RequestException("Error al intentar Registrar el SubProjecto,JSON mal Estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public SubProjectDto updateSubProject(UUID idSubProject, SubProjectDto subProjectDto) {
        return subProjectRepository.findById(idSubProject).map(subProject -> {
            subProject.setSubProjectName((subProjectDto.getSubProjectName()!=null)?subProjectDto.getSubProjectName():subProject.getSubProjectName());
            subProject.setProjectId((subProjectDto.getProjectId()!=null)?subProjectDto.getProjectId():subProject.getProjectId());
            subProject.setTeamId((subProjectDto.getTeamId()!=null)?subProjectDto.getTeamId():subProject.getTeamId());
            return modelMapper.map(subProjectRepository.save(subProject),SubProjectDto.class);
        }).orElseThrow(()->new MessageGeneric("Error, No se encontro el SubProjecto a Actualizar","404",HttpStatus.NOT_FOUND));
    }
}
