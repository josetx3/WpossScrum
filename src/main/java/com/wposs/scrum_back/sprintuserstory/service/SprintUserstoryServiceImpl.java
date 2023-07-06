package com.wposs.scrum_back.sprintuserstory.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstory;
import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstoryPk;
import com.wposs.scrum_back.sprintuserstory.repository.SprintUserstoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SprintUserstoryServiceImpl implements SprintUserstoryService {
    @Autowired
    private SprintUserstoryRepository sprintUserstoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SprintUserstoryDto saveSprintUserStory(SprintUserstoryDto sprintUserstoryDto) {
        SprintUserstory sprintUserstory = modelMapper.map(sprintUserstoryDto,SprintUserstory.class);
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
}
