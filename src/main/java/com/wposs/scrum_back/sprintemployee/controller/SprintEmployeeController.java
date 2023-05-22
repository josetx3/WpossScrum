package com.wposs.scrum_back.sprintemployee.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.service.SprintEmployeeService;
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
            @ApiResponse(responseCode = "200",description = "Get By Id Spint Employee"),
            @ApiResponse(responseCode = "404",description = "Not Found By Id Sprint Employee")
    })
    public ResponseEntity<SprintEmployeeDto> getByIdSprintEmploye(@PathVariable("id") Long idSprintEmployee){
        return sprintEmployeeService.getBySprintEmployeeId(idSprintEmployee).map(sprintEmployeeDto -> new ResponseEntity<>(sprintEmployeeDto,HttpStatus.OK)).orElse(null);
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
}
