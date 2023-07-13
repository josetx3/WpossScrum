package com.wposs.scrum_back.team.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.employe.repository.EmployeeRepository;
import com.wposs.scrum_back.team.dto.TeamDto;
import com.wposs.scrum_back.team.entity.Team;
import com.wposs.scrum_back.team.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamRepository teamRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<TeamDto> getAllTeam() {
        return teamRepository.findAll().stream().map(team -> {
            return modelMapper.map(team,TeamDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamToArea(UUID idArea) {
        return teamRepository.getByAreaId(idArea).stream().map(team -> {
            return modelMapper.map(team,TeamDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<TeamDto> getTeamByiId(UUID idTeam) {
        return Optional.ofNullable(teamRepository.findById(idTeam).map(team -> {
            return modelMapper.map(team,TeamDto.class);
        }).orElseThrow(()->new MessageGeneric("No esta disponible el Equipo que esta solcitando","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto) {
        Team team = modelMapper.map(teamDto,Team.class);
        if (teamRepository.existsByTeamName(team.getTeamName())){
            throw new MessageGeneric("Ya se encuentra un equipo con: "+team.getTeamName(),"409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(teamRepository.save(team),TeamDto.class);
        }catch (Exception ex){
            throw new RequestException("Surjio un error al intentar Registrar el equipo,JSON mal estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public TeamDto updateTeam(UUID idTeam, TeamDto teamDto) {
        Team teamP = modelMapper.map(teamDto,Team.class);
        if (teamRepository.existsByTeamName(teamP.getTeamName())){
            throw new MessageGeneric("Ya se encuentra Registrado un equipo con : "+teamP.getTeamName(),"409",HttpStatus.CONFLICT);
        }
        return teamRepository.findById(idTeam).map(team -> {
            team.setTeamName((teamDto.getTeamName()!=null)?teamDto.getTeamName():team.getTeamName());
            return modelMapper.map(teamRepository.save(team),TeamDto.class);
        }).orElseThrow(()-> new MessageGeneric("Error al intentar actualizar el Equipo no esta Disponible","404",HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public TeamDto saveEmployeToTeam(List<UUID> employeId, UUID idTeam) {
        Team team = teamRepository.findById(idTeam).orElseThrow(() -> new MessageGeneric("No se encuentra el equipo para asignar los empleados", "404", HttpStatus.NOT_FOUND));
        List<Employee> employees = employeeRepository.findAllById(employeId);
       try {
           team.getEmployees().addAll(employees);
           TeamDto teamDto = modelMapper.map(teamRepository.save(team),TeamDto.class);
           return teamDto;
        } catch (Exception ex) {
            throw new InternalServerException("Surgio un error inesperado en la inserción de empleados al equipop", "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
