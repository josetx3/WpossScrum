package com.wposs.scrum_back.employe.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.service.EmployeService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by UUID")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<EmployeDto> findById(@PathVariable("id") UUID idEmploye) {
        return employeService.getEmployeId(idEmploye).map(employeDto -> new ResponseEntity<>(employeDto,HttpStatus.OK)).orElse(null);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all employees")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<EmployeDto>> findAll(@RequestHeader(value="Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                List<EmployeDto> employeDtos = employeService.getAllEmploye();
                if (!employeDtos.isEmpty()){
                    return new ResponseEntity<>(employeDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

    }


    @PostMapping("/save/")
    @Operation(summary = "Create employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "employee created"),
            @ApiResponse(responseCode = "400",description = "employee bad request")
    })
    public ResponseEntity<EmployeDto> create(@Valid @RequestBody EmployeDto employeeDto, BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeService.seveEmploye(employeeDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<EmployeDto> updateEmployee(@PathVariable("id") UUID employeeId,@RequestBody @Valid EmployeDto employeeDto,BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeService.updateEmploye(employeeId,employeeDto),HttpStatus.OK);
    }

    @GetMapping("employeteam/{id}")
    @Operation(summary = "Get all employee to team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeToTeam(@PathVariable("id") UUID idTeam){
        List<EmployeDto> employeDtos = employeService.getEmployeToTeam(idTeam);
        if (!employeDtos.isEmpty()){
            return new ResponseEntity<>(employeDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("employeetoteam/{id}")
    @Operation(summary = "Get all employee to team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeToTeam2(@PathVariable("id") UUID idTeam){
        List<EmployeDto> employeDtos = employeService.getEmployeToTeam2(idTeam);
        if (!employeDtos.isEmpty()){
            return new ResponseEntity<>(employeDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("employeenoteam/{id}")
    @Operation(summary = "Get All Employee no Exists And To Team")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeeNoExistsToTeam(@PathVariable("id") UUID idTeam){
        List<EmployeDto> employeDtos = employeService.getAllEmployeeNoExitsAndTeam(idTeam);
        if (!employeDtos.isEmpty()){
            return new ResponseEntity<>(employeDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
