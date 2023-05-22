package com.wposs.scrum_back.project.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.project.dto.ProjectDto;
import com.wposs.scrum_back.project.service.ProjectService;
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
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    @Operation(summary = "Get project by UUID")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<ProjectDto> findById(@PathVariable("id") UUID id){
        return projectService.getProjectId(id).map(projectDto -> new ResponseEntity<>(projectDto,HttpStatus.OK)).orElse(null);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all projects")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ProjectDto>> findAll(){
        List<ProjectDto> projectDtos = projectService.gatAllProject();
        if(!projectDtos.isEmpty()){
            return new ResponseEntity<>(projectDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    @Operation(summary = "Create project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "project created"),
            @ApiResponse(responseCode = "400",description = "project bad request")
    })
    public ResponseEntity<ProjectDto> create(@Valid @RequestBody ProjectDto projectDto,BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectService.saveProject(projectDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated project"),
            @ApiResponse(responseCode = "404",description = "Project Not Found")
    })
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto projectDto, @PathVariable("id") UUID projectId, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectService.updateProject(projectId,projectDto),HttpStatus.OK);
    }

    @GetMapping("/area/{areaId}")
    @Operation(summary = "Get all projects by area id")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ProjectDto>> findAllProjectsByAreaId(@PathVariable UUID areaId){
        List<ProjectDto> projectDtos = projectService.getProjectToArea(areaId);
        if (!projectDtos.isEmpty()){
            return new ResponseEntity<>(projectDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
