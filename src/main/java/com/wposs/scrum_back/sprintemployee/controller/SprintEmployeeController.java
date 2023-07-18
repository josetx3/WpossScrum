package com.wposs.scrum_back.sprintemployee.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.service.SprintEmployeeService;
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
import java.util.List;
import java.util.UUID;
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/sprintEmployee")
public class SprintEmployeeController {
    @Autowired
    private SprintEmployeeService sprintEmployeeService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/allsprintemployee")
    @Operation(summary = "Get All Sprint Employee")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Fount Sprint Employee")
    })
    public ResponseEntity<List<SprintEmployeeDtoRequest>> getAllSprintEmployee(@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<SprintEmployeeDtoRequest> sprintEmployeeDtoRequests = sprintEmployeeService.getAllSprintEmployee();
            if (!sprintEmployeeDtoRequests.isEmpty()){
                return new ResponseEntity<>(sprintEmployeeDtoRequests, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/sprintemployee/{id}/{idSprint}")
    @Operation(summary = "Get sprint Employee By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get By Id Sprint Employee"),
            @ApiResponse(responseCode = "404",description = "Not Found By Id Sprint Employee")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> getByIdSprintEmploye(@PathVariable("id") UUID idEmployee,@PathVariable("idSprint") UUID idSprint,@RequestHeader(value="Authorization") String token){
        if(jwtUtil.validateToken(token)) {
            return sprintEmployeeService.getBySprintEmployeeId(idEmployee, idSprint).map(SprintEmployeeDtoRequest -> new ResponseEntity<>(SprintEmployeeDtoRequest, HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/savesprintemployee")
    @Operation(summary = "Save To Sprint Employee")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success Sprint Employe"),
            @ApiResponse(responseCode = "400",description = "Bad Request Json")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> saveSprintEmployee(@Valid @RequestBody SprintEmployeeDtoRequest sprintEmployeeDtoRequest, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException("error mal estructura en el JSON","400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(sprintEmployeeService.saveSprintEmployee(sprintEmployeeDtoRequest),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }


    @GetMapping("/employeeteam/{idSprint}/{idTeam}")
    @Operation(summary = "Get all employee to team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<SprintEmployeeDto>> getAllEmployeToTeam(@PathVariable("idSprint") UUID idSprint, @PathVariable("idTeam") UUID idTeam,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<SprintEmployeeDto> sprintEmployeeDtos = sprintEmployeeService.getEmployeToTeam(idSprint, idTeam);
            if (!sprintEmployeeDtos.isEmpty()){
                return new ResponseEntity<>(sprintEmployeeDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PutMapping("/updatesprintemployee/{id}/{idSprint}")
    @Operation(summary = "Update the sprint employee")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated employee"),
            @ApiResponse(responseCode = "404",description = "Employe Not Found")
    })
    public ResponseEntity<SprintEmployeeDtoRequest> updateSprintEmployee(@PathVariable("id") UUID idEmployee,@PathVariable("idSprint") UUID idSprint,@RequestHeader(value="Authorization") String token,@RequestBody @Valid SprintEmployeeDtoRequest sprintEmployeeDtoRequest,BindingResult result) {
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(sprintEmployeeService.updateSprintEmployee(idEmployee,idSprint, sprintEmployeeDtoRequest),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
