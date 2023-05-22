package com.wposs.scrum_back.employe.service;

import com.wposs.scrum_back.employe.dto.EmployeDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeService {
    List<EmployeDto> getAllEmploye();
    Optional<EmployeDto> getEmployeId(UUID idEmploye);
    EmployeDto seveEmploye(EmployeDto employeDto);
    EmployeDto updateEmploye(UUID idEmploye, EmployeDto employeDto);
    List<EmployeDto> getEmployeToTeam(UUID idTeam);
}
