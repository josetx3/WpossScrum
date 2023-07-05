package com.wposs.scrum_back.team.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.team.dto.TeamDto;
import com.wposs.scrum_back.team.dto.TeamEmployeDto;
import com.wposs.scrum_back.team.service.TeamService;
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
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get team by UUID")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<TeamDto> findById(@PathVariable("id") UUID idTeam, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return teamService.getTeamByiId(idTeam).map(teamDto -> new ResponseEntity<>(teamDto,HttpStatus.OK)).orElse(null);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful search"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<TeamDto>> findAll(@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                List<TeamDto> teamDtos = teamService.getAllTeam();
                if(!teamDtos.isEmpty()){
                    return new ResponseEntity<>(teamDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

    }

    @PostMapping("/save")
    @Operation(summary = "Create team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Team Create"),
            @ApiResponse(responseCode = "400",description = "team bad request")
    })
    public ResponseEntity<TeamDto> create(@Valid @RequestBody TeamDto teamDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                if (result.hasErrors()){
                    throw  new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(teamService.saveTeam(teamDto),HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated team"),
            @ApiResponse(responseCode = "404",description = "Project Not Found")
    })
    public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamDto team, @PathVariable("id") UUID teamId,BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                if (result.hasErrors()){
                    throw  new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(teamService.updateTeam(teamId,team),HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @GetMapping("/area/{areaId}")
    @Operation(summary = "Get all teams by area id")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<TeamDto>> findAllProjectsByAreaId(@PathVariable UUID areaId, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                List<TeamDto> teamDtos = teamService.getTeamToArea(areaId);
                if ((!teamDtos.isEmpty())){
                    return new ResponseEntity<>(teamDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @PutMapping("saveemployetoteam/{id}")
    @Operation(summary = "Save to employee To team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "400",description = "Error when inserting the employee in the team"),
            @ApiResponse(responseCode = "500",description = "An internal error occurred")
    })
    public ResponseEntity<?> saveEmployeeToTeam(@PathVariable("id") UUID idTeam,@RequestBody List<UUID> idEmployess,BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                try{
                    if(result.hasErrors()){
                        throw new MethodArgumentNotValidException("ocurrio un error inesperado en los datos recibidos","400",HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>(teamService.saveEmployeToTeam(idEmployess,idTeam),HttpStatus.CREATED);
                }catch (Exception e) {
                    return ResponseEntity.badRequest().body(e);
                }
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }
}
