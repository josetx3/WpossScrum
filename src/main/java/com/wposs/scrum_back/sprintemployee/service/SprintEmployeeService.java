package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;

import java.util.List;
import java.util.Optional;

public interface SprintEmployeeService {
    List<SprintEmployeeDto> getAllSprintEmployee();
    Optional<SprintEmployeeDto> getBySprintEmployeeId(long idSprintEmploeye);
    SprintEmployeeDto saveSprintEmployee(SprintEmployeeDto sprintEmployeeDto);
}
