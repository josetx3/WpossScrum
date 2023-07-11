package com.wposs.scrum_back.employe.controller;


import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    private EmployeService employeService;
    @PostMapping(value = "/auth/login")
    public ResponseEntity login(@RequestBody EmployeDto employee){
        return  employeService.login(employee.getEmployeeEmail(),employee.getEmployeePassword());
    }
}