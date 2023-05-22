package com.wposs.scrum_back.taskteam.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.taskteam.service.TaskTeamService;
import com.wposs.scrum_back.taskteam.service.TaskTeamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping("/taskteam")
public class TaskTeamController {
    @Autowired
    private TaskTeamService teamService;

    @PostMapping("/createtask")
    @Operation(summary = "Create task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "task created"),
            @ApiResponse(responseCode = "400",description = "task bad request")
    })
    public ResponseEntity<TaskTeamDto> create(@Valid @RequestBody TaskTeamDto taskTeamDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.saveTaskTeam(taskTeamDto),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all task")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<TaskTeamDto>> findAll(){
        List<TaskTeamDto> taskTeamDtos = teamService.getAllTaskTeam();
        if (!taskTeamDtos.isEmpty()){
            return new ResponseEntity<>(taskTeamDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{taskTeamId}")
    @Operation(summary = "Get task by UUID")
    @ApiResponse(responseCode = "200",description = "success")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<TaskTeamDto> findById(@PathVariable UUID taskTeamId){
        return teamService.getTaskTeamById(taskTeamId).map(taskTeamDto -> new ResponseEntity<>(taskTeamDto,HttpStatus.OK))
                .orElse(null);
    }

    @PutMapping("/updatetask/{id}")
    @Operation(summary = "Update Task Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Task Team Success"),
            @ApiResponse(responseCode = "400",description = "Update Task Team Bad Request")
    })
    public ResponseEntity<TaskTeamDto> updateTaskTeam(@Valid @RequestBody TaskTeamDto taskTeamDto,@PathVariable("id") UUID idTaskTeam,BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.updateTaskTeam(idTaskTeam,taskTeamDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteTaskTeam/{id}")
    @Operation(summary = "Delete Task Team To Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "Task Team Not Found")
    })
    public ResponseEntity deleteTaskTeam(@PathVariable("id") UUID idTaskTeam){
        if (teamService.deleteTaskTeam(idTaskTeam)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("taskteam/{idTeam}")
    @Operation(summary = "get All taskteam to Idteam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Found TaskTeam")
    })
    public ResponseEntity<List<TaskTeamDto>> getAllTaskTeamToIdTeam(@PathVariable("idTeam") UUID idTeam){
        List<TaskTeamDto> taskTeamDtos = teamService.getTaskTeamToIdTeam(idTeam);
        if(!taskTeamDtos.isEmpty()){
            return new ResponseEntity<>(taskTeamDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
