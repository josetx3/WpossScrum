package com.wposs.scrum_back.sprintemployee.service;

import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SprintEmployeeService {
    List<SprintEmployeeDtoRequest> getAllSprintEmployee();
    Optional<SprintEmployeeDtoRequest> getBySprintEmployeeId(UUID idEmployee,UUID idSprint);
    //SprintEmployeeDto saveSprintEmployee(SprintEmployeeDto sprintEmployeeDto);
    SprintEmployeeDtoRequest saveSprintEmployee(SprintEmployeeDtoRequest sprintEmployeeDtoRequest);

    List<SprintEmployeeDto> getEmployeToTeam(UUID idSprint, UUID idTeam);
    SprintEmployeeDtoRequest updateSprintEmployee(UUID idEmployee,UUID idSprint, SprintEmployeeDtoRequest sprintEmployeeDtoRequest);
    //Boolean existsSprintEmployeeById(Long idEmployee);
}
