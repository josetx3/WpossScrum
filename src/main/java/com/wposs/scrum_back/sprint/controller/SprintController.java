package com.wposs.scrum_back.sprint.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprint.service.SprintService;
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
@RequestMapping("/sprint")
public class SprintController {
    @Autowired
    private SprintService sprintService;

    @GetMapping("/all")
    @Operation(summary = "Get All Sprint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All success"),
            @ApiResponse(responseCode = "404",description = "Not Found Sprint")
    })
    public ResponseEntity<List<SprintDto>> getAllSprint(){
        List<SprintDto> sprintDtos = sprintService.getAllSprint();
        if(!sprintDtos.isEmpty()){
            return new ResponseEntity<>(sprintDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/savesprint")
    @Operation(summary = "Save Sprint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Create Success"),
            @ApiResponse(responseCode = "400",description = "Sprint Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server")
    })
    public ResponseEntity<SprintDto> saveSprint(@Valid @RequestBody SprintDto sprintDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("error mal estructura en el JSON","400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintService.saveSprint(sprintDto),HttpStatus.CREATED);
    }

    @GetMapping("/sprint/{idsprint}")
    @Operation(summary = "Get Sprint By Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Get Succes Sprint"),
            @ApiResponse(responseCode = "404",description = "Sprint Not Found")
    })
    public ResponseEntity<SprintDto> getSprintByid(@PathVariable("idsprint") UUID idSprint){
        return sprintService.sprintById(idSprint).map(sprintDto -> new ResponseEntity<>(sprintDto,HttpStatus.OK)).orElse(null);
    }

    @PutMapping("/updatesprint/{idsprint}")
    @Operation(summary = "Update Sprint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Success Sprint"),
            @ApiResponse(responseCode = "400",description = "Data Invaild"),
            @ApiResponse(responseCode = "404",description = "Sprint Not Found")
    })
    public ResponseEntity<SprintDto> updateSprint(@PathVariable("idsprint")UUID idSprint,@Valid @RequestBody SprintDto sprintDto,BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("Data no Valida: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sprintService.updateSprint(idSprint,sprintDto),HttpStatus.OK);
    }
    
}
