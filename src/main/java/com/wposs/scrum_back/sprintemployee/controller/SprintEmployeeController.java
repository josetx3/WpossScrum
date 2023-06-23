package com.wposs.scrum_back.sprintemployee.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
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
    public ResponseEntity<List<SprintEmployeeDtoRequest>> getAllSprintEmployee(){
        List<SprintEmployeeDtoRequest> sprintEmployeeDtoRequests = sprintEmployeeService.getAllSprintEmployee();
        if (!sprintEmployeeDtoRequests.isEmpty()){
            return new ResponseEntity<>(sprintEmployeeDtoRequests, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sprintemployee/{id}")
    @Operation(summary = "Get sprint Employee By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get By Id Sprint Employee"),
            @ApiResponse(responseCode = "404",description = "Not Found By Id Sprint Employee")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> getByIdSprintEmploye(@PathVariable("id") Long idEmployee){
        return sprintEmployeeService.getBySprintEmployeeId(idEmployee).map(SprintEmployeeDtoRequest -> new ResponseEntity<>(SprintEmployeeDtoRequest,HttpStatus.OK)).orElse(null);
    }


    @PostMapping("/savesprintemployee")
    @Operation(summary = "Save To Sprint Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success Sprint Employe"),
            @ApiResponse(responseCode = "400",description = "Bad Request Json")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> saveSprintEmployee(@Valid @RequestBody SprintEmployeeDtoRequest sprintEmployeeDtoRequest, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("error mal estructura en el JSON","400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintEmployeeService.saveSprintEmployee(sprintEmployeeDtoRequest),HttpStatus.CREATED);
    }


    /*
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
    }*/

    @GetMapping("/employeeteam/{idSprint}/{idTeam}")
    @Operation(summary = "Get all employee to team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<SprintEmployeeDto>> getAllEmployeToTeam(@PathVariable("idSprint") UUID idSprint, @PathVariable("idTeam") UUID idTeam){
        List<SprintEmployeeDto> sprintEmployeeDtos = sprintEmployeeService.getEmployeToTeam(idSprint, idTeam);
        if (!sprintEmployeeDtos.isEmpty()){
            return new ResponseEntity<>(sprintEmployeeDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

/*
    @PutMapping("/updatesprintemployee/{id}")
    @Operation(summary = "Update the sprint employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> updateSprintEmployee(@PathVariable("id") Long idEmployee,@RequestBody @Valid SprintEmployeeDtoRequest sprintEmployeeDtoRequest,BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintEmployeeService.updateSprintEmployee(idEmployee, sprintEmployeeDtoRequest),HttpStatus.OK);
    }*/
}
