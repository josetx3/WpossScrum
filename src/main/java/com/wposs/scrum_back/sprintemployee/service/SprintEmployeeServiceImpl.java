package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintemployee.repository.SprintEmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SprintEmployeeServiceImpl implements SprintEmployeeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SprintEmployeeRepository sprintEmployeeRepository;

    @Override
    public List<SprintEmployeeDto> getAllSprintEmployee() {
        return sprintEmployeeRepository.findAll().stream()
                .map(sprintEmployee -> {
                    return modelMapper.map(sprintEmployee,SprintEmployeeDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<SprintEmployeeDto> getBySprintEmployeeId(long idSprintEmploeye) {
        return Optional.ofNullable(sprintEmployeeRepository.findById(idSprintEmploeye).map(sprintEmployee -> modelMapper.map(sprintEmployee,SprintEmployeeDto.class))
                .orElseThrow(()->new MessageGeneric("","", HttpStatus.NOT_FOUND)));
    }

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
    }
}
