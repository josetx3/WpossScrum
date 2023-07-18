package com.wposs.scrum_back.project.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.project.dto.ProjectDto;
import com.wposs.scrum_back.project.service.ProjectService;
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
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get project by UUID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<ProjectDto> findById(@PathVariable("id") UUID id,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
             return projectService.getProjectId(id).map(projectDto -> new ResponseEntity<>(projectDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all projects")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ProjectDto>> findAll(@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<ProjectDto> projectDtos = projectService.gatAllProject();
            if(!projectDtos.isEmpty()){
                return new ResponseEntity<>(projectDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Create project")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "project created"),
            @ApiResponse(responseCode = "400",description = "project bad request")
    })
    public ResponseEntity<ProjectDto> create(@Valid @RequestBody ProjectDto projectDto,BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(projectService.saveProject(projectDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the project")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated project"),
            @ApiResponse(responseCode = "404",description = "Project Not Found")
    })
    public ResponseEntity<ProjectDto> updateProject(@Valid @RequestBody ProjectDto projectDto, @PathVariable("id") UUID projectId, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(projectService.updateProject(projectId,projectDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/area/{areaId}")
    @Operation(summary = "Get all projects by area id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ProjectDto>> findAllProjectsByAreaId(@PathVariable UUID areaId,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                List<ProjectDto> projectDtos = projectService.getProjectToArea(areaId);
                if (!projectDtos.isEmpty()){
                    return new ResponseEntity<>(projectDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
