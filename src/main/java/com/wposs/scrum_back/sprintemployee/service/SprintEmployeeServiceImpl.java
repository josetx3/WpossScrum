package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintemployee.repository.SprintEmployeeRepository;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import com.wposs.scrum_back.userstory.entity.UserStory;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SprintEmployeeServiceImpl implements SprintEmployeeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SprintEmployeeRepository sprintEmployeeRepository;

    @Override
    public List<SprintEmployeeDtoRequest> getAllSprintEmployee() {
        return sprintEmployeeRepository.findAll().stream()
                .map(sprintEmployee -> {
                    return modelMapper.map(sprintEmployee,SprintEmployeeDtoRequest.class);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<SprintEmployeeDtoRequest> getBySprintEmployeeId(Long idEmployee) {
        return Optional.ofNullable(sprintEmployeeRepository.findById(idEmployee).map(sprintEmployee -> modelMapper.map(sprintEmployee,SprintEmployeeDtoRequest.class))
                .orElseThrow(()->new MessageGeneric("","", HttpStatus.NOT_FOUND)));
    }
/*
    @Override
    public SprintEmployeeDto saveSprintEmployee(SprintEmployeeDto sprintEmployeeDto) {
        SprintEmployee sprintEmployee = modelMapper.map(sprintEmployeeDto,SprintEmployee.class);
        SprintEmployeePk sprintEmployeePk = new SprintEmployeePk(sprintEmployee.getId().getEmployeeId(),sprintEmployee.getId().getSprintId());
        if (sprintEmployeeRepository.existsById(sprintEmployeePk)){
            throw new MessageGeneric("","",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(sprintEmployeeRepository.save(sprintEmployee),SprintEmployeeDto.class);
        }catch (Exception ex){
            throw new InternalServerException("","",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    public SprintEmployeeDtoRequest saveSprintEmployee(SprintEmployeeDtoRequest sprintEmployeeDtoRequest) {
        SprintEmployee sprintEmployee = modelMapper.map(sprintEmployeeDtoRequest,SprintEmployee.class);
        SprintEmployeePk sprintEmployeePk = new SprintEmployeePk(sprintEmployee.getId().getEmployeeId(),sprintEmployee.getId().getSprintId());
        if (sprintEmployeeRepository.existsById(sprintEmployeePk)){
            throw new MessageGeneric("","",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(sprintEmployeeRepository.save(sprintEmployee),SprintEmployeeDtoRequest.class);
        }catch (Exception ex){
            throw new InternalServerException("","",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @Override
    @Transactional
    public List<SprintEmployeeDto> getEmployeToTeam(UUID idSprint, UUID idTeam) {
        List<Object[]> SprintEmployee = sprintEmployeeRepository.getEmployeToTeam(idSprint,idTeam);
        List<SprintEmployeeDto> sprintEmployeeDtos = new ArrayList<>();
       if(SprintEmployee.isEmpty()){
           throw new MessageGeneric("Error","404",HttpStatus.NOT_FOUND);
       }
        for (Object[] sprintEmployee:SprintEmployee) {
            SprintEmployeeDto sprintEmployeeDto = new SprintEmployeeDto(
                    sprintEmployee[0].toString(),
                    Integer.parseInt(sprintEmployee[1].toString()),
                    sprintEmployee[2].toString(),
                    Double.parseDouble(sprintEmployee[3].toString()),
                    Double.parseDouble(sprintEmployee[4].toString())
            );
            sprintEmployeeDtos.add(sprintEmployeeDto);
        }
        return sprintEmployeeDtos;

    }
/*
    @Override
    public SprintEmployeeDtoRequest updateSprintEmployee(UUID idEmployee, SprintEmployeeDtoRequest sprintEmployeeDtoRequest) {
        if(!existsSprintEmployeeById(sprintEmployeeDtoRequest.getIdEmployee())){
            return sprintEmployeeRepository.findById(idEmployee).map(sprintEmployee -> {
                sprintEmployee.setPercentage((sprintEmployeeDtoRequest.getPercentage()!=null)?sprintEmployeeDtoRequest.getPercentage():sprintEmployee.getPercentage());
                sprintEmployee.setDaysLeave((sprintEmployeeDtoRequest.getDaysLeave()!=null)?sprintEmployeeDtoRequest.getDaysLeave():sprintEmployee.getDaysLeave());
                sprintEmployee.setObservations((sprintEmployeeDtoRequest.getObservations()!=null)?sprintEmployeeDtoRequest.getObservations():sprintEmployee.getObservations());
                return modelMapper.map(sprintEmployeeRepository.save(sprintEmployee),SprintEmployeeDtoRequest.class);
            }).orElseThrow(()->new MessageGeneric("No se encontro el sprint de empleado a actualizar","404",HttpStatus.NOT_FOUND));
        }
        throw new MessageGeneric("","409",HttpStatus.CONFLICT);
    }*/

}
