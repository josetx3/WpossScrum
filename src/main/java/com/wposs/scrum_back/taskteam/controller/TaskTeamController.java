package com.wposs.scrum_back.taskteam.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDtoRequest;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.taskteam.service.TaskTeamService;
import com.wposs.scrum_back.taskteam.service.TaskTeamServiceImpl;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/taskteam")
public class TaskTeamController {
    @Autowired
    private TaskTeamService teamService;

    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/createtask")
    @Operation(summary = "Create task")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "task created"),
            @ApiResponse(responseCode = "400",description = "task bad request")
    })
    public ResponseEntity<TaskTeamDto> create(@Valid @RequestBody TaskTeamDto taskTeamDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(teamService.saveTaskTeam(taskTeamDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all task")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<TaskTeamDto>> findAll(@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<TaskTeamDto> taskTeamDtos = teamService.getAllTaskTeam();
            if (!taskTeamDtos.isEmpty()){
                return new ResponseEntity<>(taskTeamDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{taskTeamId}")
    @Operation(summary = "Get task by UUID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<TaskTeamDto> findById(@PathVariable UUID taskTeamId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return teamService.getTaskTeamById(taskTeamId).map(taskTeamDto -> new ResponseEntity<>(taskTeamDto,HttpStatus.OK))
                    .orElse(null);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updatetask/{id}")
    @Operation(summary = "Update Task Team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Task Team Success"),
            @ApiResponse(responseCode = "400",description = "Update Task Team Bad Request")
    })
    public ResponseEntity<TaskTeamDto> updateTaskTeam(@Valid @RequestBody TaskTeamDto taskTeamDto,@PathVariable("id") UUID idTaskTeam,BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(teamService.updateTaskTeam(idTaskTeam,taskTeamDto),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/updatetaskstate/{id}/{idboard}")
    @Operation(summary = "Update Task Team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Task Team Success"),
            @ApiResponse(responseCode = "400",description = "Update Task Team Bad Request")
    })
    public ResponseEntity<TaskTeamDto> updateTaskTeamState(@Valid @RequestBody TaskTeamDto taskTeamDto,@PathVariable("id") UUID idTaskTeam,@PathVariable("idboard") UUID idBoard,BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(teamService.updateTaskTeamState(idTaskTeam,idBoard,taskTeamDto),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deleteTaskTeam/{id}")
    @Operation(summary = "Delete Task Team To Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "Task Team Not Found")
    })
    public ResponseEntity deleteTaskTeam(@PathVariable("id") UUID idTaskTeam, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (teamService.deleteTaskTeam(idTaskTeam)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("taskteam/{idTeam}")
    @Operation(summary = "get All taskteam to Idteam")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<List<TaskTeamDto>> getAllTaskTeamToIdTeam(@PathVariable("idTeam") UUID idTeam, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                List<TaskTeamDto> taskTeamDtos = teamService.getTaskTeamToIdTeam(idTeam);
                if(!taskTeamDtos.isEmpty()){
                    return new ResponseEntity<>(taskTeamDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("taskteam/{idTeam}/{idUserStory}")
    @Operation(summary = "get All taskteam to Idteam and Iduserstory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<List<TaskTeamDtoRequest>> getAllTaskTeamToIdTeamAndIdUserStory(@PathVariable("idTeam") UUID idTeam,@PathVariable("idUserStory") UUID idUserStory, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           List<TaskTeamDtoRequest> taskTeamDtosR = teamService.getTaskTeamToIdTeamAndUserStory(idTeam, idUserStory);
           if (!taskTeamDtosR.isEmpty()) {
               return new ResponseEntity<>(taskTeamDtosR, HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("taskteamhu/{idTeam}/{idUserStory}")
    @Operation(summary = "get All taskteam to Idteam and Iduserstory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<List<TaskTeamDto>> getAllTaskTeamToIdTeamAndIdUserStory1(@PathVariable("idTeam") UUID idTeam,@PathVariable("idUserStory") UUID idUserStory, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<TaskTeamDto> taskTeamDtosR = teamService.getTaskTeamToIdTeamAndIdUserStory(idTeam, idUserStory);
            if (!taskTeamDtosR.isEmpty()) {
                return new ResponseEntity<>(taskTeamDtosR, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
