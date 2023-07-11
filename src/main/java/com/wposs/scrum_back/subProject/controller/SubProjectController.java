package com.wposs.scrum_back.subProject.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.subProject.dto.SubProjectDto;
import com.wposs.scrum_back.subProject.service.SubProjectService;
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
@RequestMapping("/subproject")
public class SubProjectController {
    @Autowired
    private SubProjectService subProjectService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{id}")
    @Operation(summary = "Get subproject by UUID")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<SubProjectDto> findById(@PathVariable UUID id,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                return subProjectService.gatSubProjectId(id).map(subProjectDto -> new ResponseEntity<>(subProjectDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all subprojects")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<SubProjectDto>> findAll(@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
               List<SubProjectDto> subProjectDtos = subProjectService.gatAllSubProject();
               if (!subProjectDtos.isEmpty()){
                   return new ResponseEntity<>(subProjectDtos,HttpStatus.OK);
               }
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Create subproject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "subproject created"),
            @ApiResponse(responseCode = "400",description = "subproject bad request")
    })
    public ResponseEntity<SubProjectDto> create(@Valid @RequestBody SubProjectDto subProjectDto, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if(result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(subProjectService.saveSubProject(subProjectDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the subproject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated subproject"),
            @ApiResponse(responseCode = "404",description = "Project Not Found")
    })
    public ResponseEntity<SubProjectDto> updateSubProject(@RequestBody SubProjectDto subProject, @PathVariable("id") UUID subProjectId,BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (result.hasErrors()){
                    throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(subProjectService.updateSubProject(subProjectId,subProject),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Get all subprojects by project id")
    @ApiResponse(responseCode = "200",description = "success")
    public ResponseEntity<List<SubProjectDto>> findAllSubProjectsByProjectId(@PathVariable UUID projectId,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                List<SubProjectDto> subProjectDtos = subProjectService.getSubProjectToProject(projectId);
                if(!subProjectDtos.isEmpty()){
                    return new ResponseEntity<>(subProjectDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
