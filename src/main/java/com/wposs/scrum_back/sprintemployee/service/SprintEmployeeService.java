package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SprintEmployeeService {
    List<SprintEmployeeDto> getAllSprintEmployee();
    Optional<SprintEmployeeDto> getBySprintEmployeeId(long idEmployee);
    SprintEmployeeDto saveSprintEmployee(SprintEmployeeDto sprintEmployeeDto);

    List<SprintEmployeeDto> getEmployeToTeam(UUID idTeam);
    //SprintEmployeeDto updateSprintEmployee(long idEmployee, SprintEmployeeDto sprintEmployeeDto);
    //Boolean existsSprintEmployeeById(long idEmployee);
}
