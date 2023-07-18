package com.wposs.scrum_back.employe.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.service.EmployeService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeService employeService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by UUID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<EmployeDto> findById(@PathVariable("id") UUID idEmploye, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
             return employeService.getEmployeId(idEmploye).map(employeDto -> new ResponseEntity<>(employeDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all employees")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<EmployeDto>> findAll(@RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
                List<EmployeDto> employeDtos = employeService.getAllEmploye();
                if (!employeDtos.isEmpty()){
                    return new ResponseEntity<>(employeDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save/")
    @Operation(summary = "Create employee")
    @SecurityRequirement(name = "bearerAuth")
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
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<EmployeDto> updateEmployee(@RequestHeader(value="Authorization") String token,@PathVariable("id") UUID employeeId,@RequestBody @Valid EmployeDto employeeDto,BindingResult result) {
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(employeService.updateEmploye(employeeId,employeeDto),HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/{password}")
    @Operation(summary = "Update the employee with new password")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<EmployeDto> updateEmployeePass(@RequestHeader(value="Authorization") String token,@PathVariable("id") UUID employeeId,@PathVariable("password") String password,@RequestBody @Valid EmployeDto employeeDto,BindingResult result) {
     if (jwtUtil.validateToken(token)){
         if (result.hasErrors()){
             throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity<>(employeService.updateEmployePass(employeeId,password,employeeDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("employeteam/{id}")
    @Operation(summary = "Get all employee to team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeToTeam(@RequestHeader(value="Authorization") String token,@PathVariable("id") UUID idTeam){
       if (jwtUtil.validateToken(token)){
           List<EmployeDto> employeDtos = employeService.getEmployeToTeam(idTeam);
           if (!employeDtos.isEmpty()){
               return new ResponseEntity<>(employeDtos,HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("employeetoteam/{id}")
    @Operation(summary = "Get all employee to team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeToTeam2(@RequestHeader(value="Authorization") String token,@PathVariable("id") UUID idTeam){
        if (jwtUtil.validateToken(token)){
                List<EmployeDto> employeDtos = employeService.getEmployeToTeam2(idTeam);
                if (!employeDtos.isEmpty()){
                    return new ResponseEntity<>(employeDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("employeenoteam/{id}")
    @Operation(summary = "Get All Employee no Exists And To Team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "")
    })
    public ResponseEntity<List<EmployeDto>> getAllEmployeeNoExistsToTeam(@RequestHeader(value="Authorization") String token,@PathVariable("id") UUID idTeam){
      if (jwtUtil.validateToken(token)){
                List<EmployeDto> employeDtos = employeService.getAllEmployeeNoExitsAndTeam(idTeam);
                if (!employeDtos.isEmpty()){
                    return new ResponseEntity<>(employeDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee by id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "DELETE SUCCESS"),
            @ApiResponse(responseCode = "404",description = "EMPLOYEE NOT FOUND")
    })
    public ResponseEntity deleteEmployee(@PathVariable("id") UUID idEmployee, @RequestHeader(value="Authorization") String token){
        if(jwtUtil.validateToken(token)){
            if(employeService.deleteEmploye(idEmployee)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
