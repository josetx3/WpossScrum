package com.wposs.scrum_back.userstorystatus.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import com.wposs.scrum_back.userstorystatus.service.UserStoryStatusService;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/userstorystatus")
public class UserStoryStatusController {
    @Autowired
    private UserStoryStatusService userStoryStatusService;

    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/statusall")
    @Operation(description = "GET ALL STATUS")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "ALL USER STORY STATUS")
    public ResponseEntity<List<UserStoryStatusDto>> getAllStatus(@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryStatusDto> userStoryStatusDtos = userStoryStatusService.gatAll();
            if (!userStoryStatusDtos.isEmpty()){
                return new ResponseEntity<>(userStoryStatusDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/savestatus")
    @Operation(description = "SAVE STATUS")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "STATUS CREATE"),
            @ApiResponse(responseCode = "400",description = "FAIL STRUCT JSON")
    })
    public ResponseEntity<UserStoryStatusDto> saveStatus(@Valid @RequestBody UserStoryStatusDto userStoryStatusDto,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return new ResponseEntity<>(userStoryStatusService.saveStatus(userStoryStatusDto),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the userStoryStatus")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the updated UserStoryStatus"),
            @ApiResponse(responseCode = "404", description = "UserStoryStatus Not Found")
    })
    public ResponseEntity<UserStoryStatusDto> updateUserStoryStatus(@RequestBody UserStoryStatusDto userStoryStatusDto, @PathVariable("id") Long idStatus, BindingResult result, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userStoryStatusService.updateUserStoryStatus(idStatus, userStoryStatusDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    @DeleteMapping("/deletestatus/{id}")
    @Operation(description = "DELETE STATUS")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "Status Not Found")
    })
    public ResponseEntity deleteStatus(@PathVariable("id") Long idStatus,@RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            if (userStoryStatusService.deleteProducto(idStatus)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
