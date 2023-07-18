package com.wposs.scrum_back.sprintuserstory.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDtoRequest;
import com.wposs.scrum_back.sprintuserstory.service.SprintUserstoryService;
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
@RequestMapping("/sprintuserstory")
public class SprintUserstoryController {
    @Autowired
    private SprintUserstoryService sprintUserstoryService;
    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/savesprintuserstory")
    @Operation(summary = "Save To Sprint story")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success Sprint  story"),
            @ApiResponse(responseCode = "400",description = "Bad Request Json")
    })
    public ResponseEntity<SprintUserstoryDto> saveSprintEmployee(@Valid @RequestBody SprintUserstoryDto sprintUserstoryDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException("error mal estructura en el JSON","400", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(sprintUserstoryService.saveSprintUserStory(sprintUserstoryDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/sprintuserstory/{idsprint}")
    @Operation(summary = "Get user story by sprint")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Succes user story"),
            @ApiResponse(responseCode = "404",description = "user story data Not Found")
    })
    public ResponseEntity<List<SprintUserstoryDtoRequest>> getDataSprint(@PathVariable("idsprint") UUID idSprint, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           return new ResponseEntity<>(sprintUserstoryService.getAllSprintUserstoryBySprint(idSprint),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/updatesprintuserstory/{idSprint}/{idUserStory}")
    @Operation(summary = "Update sprintuserstory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update sprintuserstory Success"),
            @ApiResponse(responseCode = "400",description = "Update sprintuserstory Bad Request")
    })
    public ResponseEntity<SprintUserstoryDto> updateTaskTeam(@Valid @RequestBody SprintUserstoryDto sprintUserstoryDto,@PathVariable("idUserStory") UUID idUserStory,@PathVariable("idSprint") UUID idSprint, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (result.hasErrors()){
                    throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(sprintUserstoryService.updateUserstoryService(idSprint,idUserStory,sprintUserstoryDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deletesprintuserstory/{idSprint}/{idUserStory}")
    @Operation(summary = "Delete sprintuserstory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "sprintuserstory Not Found")
    })
    public ResponseEntity deletesSprintUserStory(@PathVariable("idUserStory") UUID idUserStory,@PathVariable("idSprint") UUID idSprint, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           if (sprintUserstoryService.deleteSpringUserStory(idSprint,idUserStory)){
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

       }else{
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
    }
}
