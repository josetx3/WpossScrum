package com.wposs.scrum_back.sprint.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprint.entity.Sprint;
import com.wposs.scrum_back.sprint.repository.SprintRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public List<SprintDto> getAllSprint() {
        return sprintRepository.findAll().stream()
                .map(sprint -> {
                    return modelMapper.map(sprint,SprintDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    public SprintDto saveSprint(SprintDto sprintDto) {
        Sprint sprint = modelMapper.map(sprintDto,Sprint.class);
        System.out.printf(sprint.getDaySprint());
        try {
            modelMapper.map(sprintRepository.save(sprint),SprintDto.class);
            return sprintRepository.getBySprintCountIsNull().map(sprint1 -> {
                sprint1.setSprintCount(sprintRepository.countByAreaIdAndTeamId(sprint1.getAreaId(),sprint1.getTeamId()));
                return modelMapper.map(sprintRepository.save(sprint1),SprintDto.class);
            }).orElseThrow(()->new MessageGeneric("Error inesperado al intertar asiganr numero de sprint","400",HttpStatus.BAD_REQUEST));
        }catch (Exception ex){
            throw new InternalServerException("Surgio un Error al intetar Registrar el Sprint","500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<SprintDto> sprintById(UUID sprintId) {
        return Optional.ofNullable(sprintRepository.findById(sprintId).map(sprint -> {
            return modelMapper.map(sprint,SprintDto.class);
        }).orElseThrow(()->new MessageGeneric("No se encuentra el Spring Solicitado","404",HttpStatus.NOT_FOUND)));
    }

    @Override
    public SprintDto updateSprint(UUID sprintId, SprintDto sprintDto) {
        return sprintRepository.findById(sprintId).map(sprint -> {
            sprint.setAreaId((sprintDto.getAreaId()!=null)?sprintDto.getAreaId():sprint.getAreaId());
            sprint.setTeamId((sprintDto.getTeamId()!=null)?sprintDto.getTeamId():sprint.getTeamId());
            sprint.setSprintStart((sprintDto.getSprintStart()!=null)?sprintDto.getSprintStart():sprint.getSprintStart());
            sprint.setSprintEnd((sprintDto.getSprintEnd()!=null)?sprintDto.getSprintEnd():sprint.getSprintEnd());
            sprint.setSprintCount((sprintDto.getTeamId()==sprint.getTeamId()?sprint.getSprintCount():(sprint.getSprintCount()+1)));
            return modelMapper.map(sprintRepository.save(sprint),SprintDto.class);
        }).orElseThrow(()->new MessageGeneric("no esta disponible el Sprint que quiere actualizar","404",HttpStatus.NOT_FOUND));
    }
/*
    @Override
    public Optional<AreaDto> getFechaInit(Date sprintStart) {
        return Optional.ofNullable(sprintRepository.get(sprintStart).map(area -> {
            return modelMapper.map(area, AreaDto.class);
        }).orElseThrow(() -> new MessageGeneric("No hay fechas de inicio registradas", "404", HttpStatus.NOT_FOUND)));
    }
*/
}
