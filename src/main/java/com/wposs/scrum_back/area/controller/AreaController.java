package com.wposs.scrum_back.area.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.area.service.AreaServiceImpl;
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
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaServiceImpl areaService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Area to Id")
    @ApiResponse(responseCode = "200", description = "Area Success")
    public ResponseEntity<AreaDto> findById(@PathVariable UUID id) {
        return areaService.getAreaId(id).map(areaDto -> new ResponseEntity<>(areaDto, HttpStatus.OK)).orElse(null);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all areas")
    @ApiResponse(responseCode = "200", description = "Get All List Success")
    public ResponseEntity<List<AreaDto>> findAll() {
        List<AreaDto> areaDtos = areaService.getAllArea();
        if (!areaDtos.isEmpty()) {
            return new ResponseEntity<>(areaDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save/")
    @Operation(summary = "Create Area")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Area"),
            @ApiResponse(responseCode = "400", description = "Area Bad Request")
    })
    public ResponseEntity<AreaDto> create(@Valid @RequestBody AreaDto areaDto, BindingResult result) {
        if(result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(areaService.saveArea(areaDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the area")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the updated area"),
            @ApiResponse(responseCode = "404", description = "Area Not Found")
    })
    public ResponseEntity<AreaDto> updateArea(@RequestBody AreaDto areaDto, @PathVariable("id") UUID areaId,BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(areaService.updateArea(areaId, areaDto), HttpStatus.OK);
    }


    @DeleteMapping("/deletearea/{id}")
    @Operation(description = "DELETE AREA TO ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "DELETE SUCCESS"),
            @ApiResponse(responseCode = "404",description = "AREA NOT FOUND")
    })
    public ResponseEntity deleteArea(@PathVariable("id")UUID idArea){
        if (areaService.deleteArea(idArea)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
