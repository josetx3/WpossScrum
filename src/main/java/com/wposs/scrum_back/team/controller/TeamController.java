package com.wposs.scrum_back.team.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.team.dto.TeamDto;
import com.wposs.scrum_back.team.dto.TeamEmployeDto;
import com.wposs.scrum_back.team.service.TeamService;
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
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get team by UUID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<TeamDto> findById(@PathVariable("id") UUID idTeam, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return teamService.getTeamByiId(idTeam).map(teamDto -> new ResponseEntity<>(teamDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all teams")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful search"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<TeamDto>> findAll(@RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           List<TeamDto> teamDtos = teamService.getAllTeam();
           if(!teamDtos.isEmpty()){
               return new ResponseEntity<>(teamDtos,HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Create team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Team Create"),
            @ApiResponse(responseCode = "400",description = "team bad request")
    })
    public ResponseEntity<TeamDto> create(@Valid @RequestBody TeamDto teamDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw  new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(teamService.saveTeam(teamDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated team"),
            @ApiResponse(responseCode = "404",description = "Project Not Found")
    })
    public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamDto team, @PathVariable("id") UUID teamId,BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw  new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(teamService.updateTeam(teamId,team),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/area/{areaId}")
    @Operation(summary = "Get all teams by area id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<TeamDto>> findAllProjectsByAreaId(@PathVariable UUID areaId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<TeamDto> teamDtos = teamService.getTeamToArea(areaId);
            if ((!teamDtos.isEmpty())){
                return new ResponseEntity<>(teamDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("saveemployetoteam/{id}")
    @Operation(summary = "Save to employee To team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "400",description = "Error when inserting the employee in the team"),
            @ApiResponse(responseCode = "500",description = "An internal error occurred")
    })
    public ResponseEntity<?> saveEmployeeToTeam(@PathVariable("id") UUID idTeam,@RequestBody List<UUID> idEmployess,BindingResult result, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           if(result.hasErrors()){
               throw new MethodArgumentNotValidException("ocurrio un error inesperado en los datos recibidos","400",HttpStatus.BAD_REQUEST);
           }
          return new ResponseEntity<>(teamService.saveEmployeToTeam(idEmployess,idTeam),HttpStatus.CREATED);
       }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
    }
}
