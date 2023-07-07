package com.wposs.scrum_back.sprintuserstory.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDtoRequest;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstory;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstoryPk;
import com.wposs.scrum_back.sprintuserstory.repository.SprintUserstoryRepository;
import com.wposs.scrum_back.subProject.entity.SubProject;
import com.wposs.scrum_back.subProject.repository.SubProjectRepository;
import com.wposs.scrum_back.userstory.entity.UserStory;
import com.wposs.scrum_back.userstory.repository.UserStoryRepository;
import com.wposs.scrum_back.userstorystatus.entity.UserStoryStatus;
import com.wposs.scrum_back.userstorystatus.repository.UserStoryStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SprintUserstoryServiceImpl implements SprintUserstoryService {
    @Autowired
    private SprintUserstoryRepository sprintUserstoryRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private UserStoryStatusRepository userStoryStatusRepository;
    @Autowired
    private SubProjectRepository subProjectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SprintUserstoryDto saveSprintUserStory(SprintUserstoryDto sprintUserstoryDto) {
        SprintUserstory sprintUserstory = modelMapper.map(sprintUserstoryDto,SprintUserstory.class);
       Optional<UserStory> userStory= userStoryRepository.findById(sprintUserstory.getId().getUserStoryId());
       UserStoryStatus userStoryStatus = userStoryStatusRepository.findByUserStoryStateName("DESARROLLO");
       Long p= Long.valueOf(userStoryStatus.getUserStoryStateId());
       //
       userStory.get().setUserStoryStateId(p);
       userStoryRepository.save(userStory.get());
       SprintUserstoryPk sprintUserstoryPk = new SprintUserstoryPk(sprintUserstory.getId().getUserStoryId(),sprintUserstory.getId().getSprintId());
        if (sprintUserstoryRepository.existsById(sprintUserstoryPk)){
            throw new MessageGeneric("","", HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(sprintUserstoryRepository.save(sprintUserstory), SprintUserstoryDto.class);
        }catch (Exception ex){
            throw new InternalServerException("","",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public SprintUserstoryDto updateUserstoryService(UUID idSprint, UUID idUserStory,SprintUserstoryDto sprintUserstoryDto) {
        SprintUserstoryPk primaryKey = new SprintUserstoryPk();
        primaryKey.setSprintId(idSprint);// Establecer el valor del ID del Sprint
        primaryKey.setUserStoryId(idUserStory);

        return sprintUserstoryRepository.findByPrimaryKey1(primaryKey).map(sprintUserstory -> {
            sprintUserstory.setPoints((sprintUserstoryDto.getPoints()!=null)?sprintUserstoryDto.getPoints():sprintUserstory.getPoints());
            return modelMapper.map(sprintUserstoryRepository.save(sprintUserstory), SprintUserstoryDto.class);
        }).orElseThrow(()->new MessageGeneric("No se encontro los datos a actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public List<SprintUserstoryDtoRequest> getAllSprintUserstoryBySprint(UUID sprintId) {
        List<Object[]> UserStorySprint = sprintUserstoryRepository.findAllBySprint(sprintId);
        List<SprintUserstoryDtoRequest> sprintUserstoryDtoRequests = new ArrayList<>();

        if(UserStorySprint.isEmpty()){
            throw new MessageGeneric("Error","404",HttpStatus.NOT_FOUND);
        }
        for (Object[] sprintUserStory:UserStorySprint) {

            SubProject subProject= subProjectRepository.getBySubProjectName( sprintUserStory[2].toString());
            UUID subprojectId=subProject.getSubProjectId();
            UserStory userStory1= userStoryRepository.getByUserStoryNameAndSubProjectId(sprintUserStory[0].toString(), subprojectId);


            SprintUserstoryDtoRequest sprintUserstoryDtoRequest = new SprintUserstoryDtoRequest(
                    sprintUserStory[0].toString(),
                    sprintUserStory[1].toString(),
                    sprintUserStory[2].toString(),
                    Integer.parseInt(sprintUserStory[3].toString()),
                    sprintId,
                    userStory1.getUserStoryId()
            );
            sprintUserstoryDtoRequests.add(sprintUserstoryDtoRequest);
        }
        return sprintUserstoryDtoRequests;
    }

    @Override
    @Transactional
    public Boolean deleteSpringUserStory(UUID idSprint,UUID iDUserStory) {


        SprintUserstoryPk sprintUserstoryPk = new SprintUserstoryPk(iDUserStory,idSprint);
        if (sprintUserstoryRepository.existsById(sprintUserstoryPk)){
            sprintUserstoryRepository.deleteById(sprintUserstoryPk);
            Optional<UserStory> userStory= userStoryRepository.findById(iDUserStory);
            UserStoryStatus userStoryStatus = userStoryStatusRepository.findByUserStoryStateName("REFINADA");
            Long p= Long.valueOf(userStoryStatus.getUserStoryStateId());
            userStory.get().setUserStoryStateId(p);
            userStoryRepository.save(userStory.get());
            return true;
        }
        return false;
    }

}
