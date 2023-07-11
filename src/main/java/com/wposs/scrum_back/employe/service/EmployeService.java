package com.wposs.scrum_back.employe.service;

import com.wposs.scrum_back.employe.dto.EmployeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeService {
    List<EmployeDto> getAllEmploye();
    Optional<EmployeDto> getEmployeId(UUID idEmploye);
    EmployeDto seveEmploye(EmployeDto employeDto);
    EmployeDto updateEmploye(UUID idEmploye, EmployeDto employeDto);
    EmployeDto updateEmployePass(UUID idEmploye,String password, EmployeDto employeDto);
    Boolean deleteEmploye(UUID idEmployee);
    List<EmployeDto> getEmployeToTeam(UUID idTeam);
    List<EmployeDto> getEmployeToTeam2(UUID idTeam);
    List<EmployeDto> getAllEmployeeNoExitsAndTeam(UUID idTeam);
    ResponseEntity login(String correo, String password);

}
