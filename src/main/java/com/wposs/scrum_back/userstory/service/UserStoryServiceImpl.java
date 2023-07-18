package com.wposs.scrum_back.userstory.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.subProject.entity.SubProject;
import com.wposs.scrum_back.subProject.repository.SubProjectRepository;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import com.wposs.scrum_back.userstory.dto.UserStoryDtoRequest;
import com.wposs.scrum_back.userstory.entity.UserStory;
import com.wposs.scrum_back.userstory.repository.UserStoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService{
    @Autowired
    private UserStoryRepository userStoryRepository;

    @Autowired
    private SubProjectRepository subProjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserStoryDto> getAllUserStory() {
        return userStoryRepository.findAll().stream().map(userStory -> {
            return modelMapper.map(userStory,UserStoryDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserStoryDto> getAllUserStoryToSubProject(UUID idSubProject) {
        return userStoryRepository.findBySubProjectId(idSubProject).stream()
                .map(userStory -> {
                    return modelMapper.map(userStory,UserStoryDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<UserStoryDto> getUserStoryById(UUID idUserStory) {
        return Optional.ofNullable(userStoryRepository.findById(idUserStory)
                .map(userStory ->modelMapper.map(userStory,UserStoryDto.class))
                .orElseThrow(()->new MessageGeneric("No se encunetra la Historia de Usuario que esta Solicitando","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public UserStoryDto saveUserStory(UserStoryDto userStoryDto) {
        UserStory userStory = modelMapper.map(userStoryDto,UserStory.class);
        String codeUserStory= userStoryRepository.getLastCodeUserStory(userStory.getSubProjectId());
        String codeUserStoryNew;
        if(codeUserStory==null){
            codeUserStoryNew = "HU001";
        }else{
            String numeroStr = codeUserStory.substring(2);
            int numero = Integer.parseInt(numeroStr);
            numero++;
            String nuevoNumeroStr = String.format("%03d", numero);
            codeUserStoryNew = "HU" + nuevoNumeroStr;
        }
        userStory.setUserStoryCode(codeUserStoryNew);

        if(userStoryRepository.existsByUserStoryNameAndSubProjectId(userStory.getUserStoryName(),userStory.getSubProjectId())){
            throw new MessageGeneric("Error al intentar Registrar: "+userStory.getUserStoryName()+" Ya se encuentra Registrada al mismo SubProjecto","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(userStoryRepository.save(userStory),UserStoryDto.class);
        }catch (Exception ex){
            throw new RequestException("Surjio un Error al intertar Registrar la Historia de Usuario,JSON mal estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserStoryDto updateUserStory(UUID idUserStory, UserStoryDto userStoryDto) {

        return userStoryRepository.findById(idUserStory).map(userStory -> {
            userStory.setUserStoryName((userStoryDto.getUserStoryName() != null)? userStoryDto.getUserStoryName(): userStory.getUserStoryName());
            userStory.setUserStoryArchive((userStoryDto.getUserStoryArchive() != null)? userStoryDto.getUserStoryArchive() : userStory.getUserStoryArchive());
            userStory.setUserStoryScore((userStoryDto.getUserStoryScore() != null) ? userStoryDto.getUserStoryScore() : userStory.getUserStoryScore());
            userStory.setUserStoryStateId((userStoryDto.getUserStoryStateId() != null) ? userStoryDto.getUserStoryStateId(): userStory.getUserStoryStateId());
            userStory.setSubProjectId((userStoryDto.getSubProjectId() != null)? userStoryDto.getSubProjectId() : userStory.getSubProjectId());
            return modelMapper.map(userStoryRepository.save(userStory),UserStoryDto.class);
        }).orElseThrow(()-> new MessageGeneric("No se encunetra la Historia de Usuario a Actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public List<UserStoryDto> getAllUserStoryToTeam(UUID idTeam) {
        return userStoryRepository.getAllSserStoryToTeam(idTeam)
                .stream()
                .map(userStory -> {
                    return modelMapper.map(userStory,UserStoryDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserStoryDtoRequest> getAllUserStoryRef(UUID idTeam, UUID idArea) {
        List<Object[]> UserStory = userStoryRepository.getAllUserStoryRef(idTeam,idArea);
        List<UserStoryDtoRequest> userStoryDtoRequests = new ArrayList<>();

        if(UserStory.isEmpty()){
            throw new MessageGeneric("Error","404",HttpStatus.NOT_FOUND);
        }
        for (Object[] userStory:UserStory) {
            SubProject subProject= subProjectRepository.getBySubProjectName( userStory[3].toString());
            UUID subprojectId=subProject.getSubProjectId();
            UserStory userStory1= userStoryRepository.getByUserStoryNameAndSubProjectId(userStory[0].toString(), subprojectId);
            UserStoryDtoRequest userStoryDtoRequest = new UserStoryDtoRequest(
                    userStory[0].toString(),
                    Integer.parseInt(userStory[1].toString()),
                    userStory[2].toString(),
                    userStory[3].toString(),
                    userStory[4].toString(),
                    userStory[5].toString(),
                    userStory[6].toString(),
                    userStory1.getUserStoryId()
            );
            userStoryDtoRequests.add(userStoryDtoRequest);
        }
        return userStoryDtoRequests;

    }

    @Override
    @Transactional
    public List<UserStoryDto> getAllUserStoryByTeam(UUID teamId, UUID sprintId) {
        return userStoryRepository.getUserStoryByTeamAndSprint(teamId,sprintId).stream()
                .map(userStory -> {
                    return modelMapper.map(userStory, UserStoryDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserStoryDto> getAllBoardsByTeamAndAreaAndSprint(UUID areaId, UUID teamId, UUID sprintId) {
        return userStoryRepository.getUserStoryByAreaAndTeamAndSprint(areaId,teamId,sprintId).stream().map(userStory -> {
            return modelMapper.map(userStory,UserStoryDto.class);
        }).collect(Collectors.toList());
    }
}
