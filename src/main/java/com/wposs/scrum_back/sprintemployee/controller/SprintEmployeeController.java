package com.wposs.scrum_back.sprintemployee.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import com.wposs.scrum_back.sprintemployee.service.SprintEmployeeService;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sprintEmployee")
public class SprintEmployeeController {
    @Autowired
    private SprintEmployeeService sprintEmployeeService;

    @GetMapping("/allsprintemployee")
    @Operation(summary = "Get All Sprint Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Fount Sprint Employee")
    })
    public ResponseEntity<List<SprintEmployeeDto>> getAllSprintEmployee(){
        List<SprintEmployeeDto> sprintEmployeeDtos = sprintEmployeeService.getAllSprintEmployee();
        if (!sprintEmployeeDtos.isEmpty()){
            return new ResponseEntity<>(sprintEmployeeDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sprintemployee/{id}")
    @Operation(summary = "Get sprint Employee By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get By Id Sprint Employee"),
            @ApiResponse(responseCode = "404",description = "Not Found By Id Sprint Employee")
    })
    public ResponseEntity<SprintEmployeeDto> getByIdSprintEmploye(@PathVariable("id") long idEmployee){
        return sprintEmployeeService.getBySprintEmployeeId(idEmployee).map(sprintEmployeeDto -> new ResponseEntity<>(sprintEmployeeDto,HttpStatus.OK)).orElse(null);
    }


    @PostMapping("/savesprintemployee")
    @Operation(summary = "Save To Sprint Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success Sprint Employe"),
            @ApiResponse(responseCode = "400",description = "Bad Request Json")
    })
    public ResponseEntity<SprintEmployeeDto> saveSprintEmployee(@Valid @RequestBody SprintEmployeeDto sprintEmployeeDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("error mal estructura en el JSON","400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintEmployeeService.saveSprintEmployee(sprintEmployeeDto),HttpStatus.CREATED);
    }

    @GetMapping("employeeteam/{id}")
    @Operation(summary = "Get all employee to team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<SprintEmployeeDto>> getAllEmployeToTeam(@PathVariable("id") UUID idTeam){
        List<SprintEmployeeDto> sprintEmployeeDtos = sprintEmployeeService.getEmployeToTeam(idTeam);
        if (!sprintEmployeeDtos.isEmpty()){
            return new ResponseEntity<>(sprintEmployeeDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



/*
    @PutMapping("/sprintemployee/{id}")
    @Operation(summary = "Update the sprint employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<SprintEmployeeDto> updateSprintEmployee(@PathVariable("id") long idEmployee,@RequestBody @Valid SprintEmployeeDto sprintEmployeeDto,BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintEmployeeService.updateSprintEmployee(idEmployee, sprintEmployeeDto),HttpStatus.OK);
    }*/
}
