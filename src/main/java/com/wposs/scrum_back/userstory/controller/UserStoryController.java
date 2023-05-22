package com.wposs.scrum_back.userstory.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import com.wposs.scrum_back.userstory.entity.UserStory;
import com.wposs.scrum_back.userstory.service.UserStoryService;
import com.wposs.scrum_back.userstory.service.UserStoryServiceImpl;
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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userstory")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;

    @GetMapping("/{userStoryId}")
    @Operation(summary = "Get UserStory To Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful search"),
            @ApiResponse(responseCode = "404",description = "Not Found UserStory")
    })
    @ApiResponse(responseCode = "200",description = "")
    public ResponseEntity<UserStoryDto> finById(@PathVariable("userStoryId") UUID userStoryId){
        return userStoryService.getUserStoryById(userStoryId)
                .map(userStoryDto -> new ResponseEntity<>(userStoryDto,HttpStatus.OK)).orElse(null);
    }

    @GetMapping("/userstory/all")
    @Operation(summary = "Get all User Stories")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<UserStoryDto>> findAll(){
        List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStory();
        if(!userStoryDtos.isEmpty()){
            return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    @Operation(summary = "Create User Story")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "user story created"),
            @ApiResponse(responseCode = "400",description = "user story bad request")
    })
    public ResponseEntity<UserStoryDto> create(@Valid @RequestBody UserStoryDto userStoryDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStoryService.saveUserStory(userStoryDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the userStory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the updated User story"),
            @ApiResponse(responseCode = "400",description = "Story Not Found")
    })
    public ResponseEntity<UserStoryDto> updateUserStory(@Valid @RequestBody UserStoryDto userStoryDto, @PathVariable("id") UUID userStoryId,BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userStoryService.updateUserStory(userStoryId,userStoryDto),HttpStatus.OK);
    }

    @GetMapping("/subproject/{subprojectId}")
    @Operation(summary = "Get all user stories by subproject id")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<UserStoryDto>> findAllUserStoriesBySubProjectId(@PathVariable("subprojectId") UUID subprojectId){
        List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStoryToSubProject(subprojectId);
        if (!userStoryDtos.isEmpty()){
            return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/userstoryteam/{id}")
    @Operation(summary = "Get All UserStory To Team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<UserStoryDto>> getAllUserStoryToTeam(@PathVariable("id")UUID idTeam){
        List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStoryToTeam(idTeam);
        if(userStoryDtos.isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
    }
}
