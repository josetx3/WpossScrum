package com.wposs.scrum_back.sprintuserstory.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintuserstory.dto.SprintUserstoryDto;
import com.wposs.scrum_back.sprintuserstory.service.SprintUserstoryService;
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


@RestController
@RequestMapping("/sprintuserstory")
public class SprintUserstoryController {

    @Autowired
    private SprintUserstoryService sprintUserstoryService;
    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/savesprintuserstory")
    @Operation(summary = "Save To Sprint story")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success Sprint  story"),
            @ApiResponse(responseCode = "400",description = "Bad Request Json")
    })
    public ResponseEntity<SprintUserstoryDto> saveSprintEmployee(@Valid @RequestBody SprintUserstoryDto sprintUserstoryDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                try{
                    if (result.hasErrors()){
                        throw new MethodArgumentNotValidException("error mal estructura en el JSON","400", HttpStatus.BAD_REQUEST);
                    }
                        return new ResponseEntity<>(sprintUserstoryService.saveSprintUserStory(sprintUserstoryDto),HttpStatus.CREATED);
                }catch (Exception e){
                    return ResponseEntity.badRequest().build();
                }

            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }
}
