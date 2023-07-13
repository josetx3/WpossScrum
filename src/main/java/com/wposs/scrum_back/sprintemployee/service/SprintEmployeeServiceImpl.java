package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.employe.repository.EmployeeRepository;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintemployee.repository.SprintEmployeeRepository;
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
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<SprintEmployeeDtoRequest> getAllSprintEmployee() {
        return sprintEmployeeRepository.findAll().stream()
                .map(sprintEmployee -> {
                    return modelMapper.map(sprintEmployee,SprintEmployeeDtoRequest.class);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<SprintEmployeeDtoRequest> getBySprintEmployeeId(UUID idEmployee, UUID idSprint) {
        SprintEmployeePk primaryKey = new SprintEmployeePk();
        primaryKey.setSprintId(idSprint); // Establecer el valor del ID del Sprint
        primaryKey.setEmployeeId(idEmployee);
        return Optional.ofNullable(sprintEmployeeRepository.findByPrimaryKey(primaryKey).map(sprintEmployee -> modelMapper.map(sprintEmployee,SprintEmployeeDtoRequest.class))
                .orElseThrow(()->new MessageGeneric("","", HttpStatus.NOT_FOUND)));
    }

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
            Employee employee=employeeRepository.findByEmployeeEmail(sprintEmployee[1].toString());
            SprintEmployeeDto sprintEmployeeDto = new SprintEmployeeDto(
                    sprintEmployee[0].toString(),
                    sprintEmployee[1].toString(),
                    employee.getEmployeeId(),
                    Integer.parseInt(sprintEmployee[2].toString()),
                    sprintEmployee[3].toString(),
                    Double.parseDouble(sprintEmployee[4].toString()),
                    Double.parseDouble(sprintEmployee[5].toString())
            );
            sprintEmployeeDtos.add(sprintEmployeeDto);
        }
        return sprintEmployeeDtos;
    }

    @Override
    public SprintEmployeeDtoRequest updateSprintEmployee(UUID idEmployee,UUID idSprint, SprintEmployeeDtoRequest sprintEmployeeDtoRequest) {
        SprintEmployeePk primaryKey = new SprintEmployeePk();
        primaryKey.setSprintId(idSprint); // Establecer el valor del ID del Sprint
        primaryKey.setEmployeeId(idEmployee);

            return sprintEmployeeRepository.findByPrimaryKey(primaryKey).map(sprintEmployee -> {
                sprintEmployee.setPercentage((sprintEmployeeDtoRequest.getPercentage()!=null)?sprintEmployeeDtoRequest.getPercentage():sprintEmployee.getPercentage());
                sprintEmployee.setPercentageFinal((sprintEmployeeDtoRequest.getPercentageFinal()!=null)?sprintEmployeeDtoRequest.getPercentageFinal():sprintEmployee.getPercentageFinal());
                sprintEmployee.setDaysLeave((sprintEmployeeDtoRequest.getDaysLeave()!=null)?sprintEmployeeDtoRequest.getDaysLeave():sprintEmployee.getDaysLeave());
                sprintEmployee.setObservations((sprintEmployeeDtoRequest.getObservations()!=null)?sprintEmployeeDtoRequest.getObservations():sprintEmployee.getObservations());
                return modelMapper.map(sprintEmployeeRepository.save(sprintEmployee),SprintEmployeeDtoRequest.class);
            }).orElseThrow(()->new MessageGeneric("No se encontro el sprint de empleado a actualizar","404",HttpStatus.NOT_FOUND));
    }
}
