package com.wposs.scrum_back.sprint.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.area.entity.Area;
import com.wposs.scrum_back.area.repository.AreaRepository;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprint.dto.SprintDtoRequest;
import com.wposs.scrum_back.sprint.entity.Sprint;
import com.wposs.scrum_back.sprint.repository.SprintRepository;
import com.wposs.scrum_back.team.entity.Team;
import com.wposs.scrum_back.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AreaRepository areaRepository;

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

    public List<SprintDto> getSprintByTeam(UUID teamId){
        return sprintRepository.getByTeamId(teamId).stream().map(sprint -> {
            return modelMapper.map(sprint, SprintDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public SprintDto updateSprint(UUID sprintId, SprintDto sprintDto) {
        return sprintRepository.findById(sprintId).map(sprint -> {
            sprint.setAreaId((sprintDto.getAreaId()!=null)?sprintDto.getAreaId():sprint.getAreaId());
            sprint.setTeamId((sprintDto.getTeamId()!=null)?sprintDto.getTeamId():sprint.getTeamId());
            sprint.setSprintStart((sprintDto.getSprintStart()!=null)?sprintDto.getSprintStart():sprint.getSprintStart());
            sprint.setSprintEnd((sprintDto.getSprintEnd()!=null)?sprintDto.getSprintEnd():sprint.getSprintEnd());
            sprint.setSprintCount((sprintDto.getTeamId()==sprint.getTeamId()?sprint.getSprintCount():(sprint.getSprintCount()+1)));
            sprint.setSprintDaysDate((sprintDto.getSprintDaysDate()!=null)?sprintDto.getSprintDaysDate(): sprint.getSprintDaysDate());
            return modelMapper.map(sprintRepository.save(sprint),SprintDto.class);
        }).orElseThrow(()->new MessageGeneric("no esta disponible el Sprint que quiere actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public SprintDtoRequest getDataSprint(UUID idSprint) {
        List<Object[]> data = sprintRepository.getDataSprint(idSprint);
        SprintDtoRequest dataRequest=null;

        for (Object[] sprintData:data) {
            Team team= teamRepository.getByTeamName(sprintData[1].toString());
            Area area= areaRepository.getByAreaName(sprintData[0].toString());
            //System.out.println(team.getTeamId());
                dataRequest = new SprintDtoRequest(
                        sprintData[0].toString(),
                        sprintData[1].toString(),
                        area.getAreaId(),
                        team.getTeamId(),
                        Integer.parseInt(sprintData[2].toString()),
                        Double.parseDouble(sprintData[3].toString())
                        );

        }
        return dataRequest;
    }


}
