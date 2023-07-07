package com.wposs.scrum_back.userstorystatus.controller;

import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import com.wposs.scrum_back.userstorystatus.service.UserStoryStatusService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/userstorystatus")
public class UserStoryStatusController {
    @Autowired
    private UserStoryStatusService userStoryStatusService;

    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/statusall")
    @Operation(description = "GET ALL STATUS")
    @ApiResponse(responseCode = "200",description = "ALL USER STORY STATUS")
    public ResponseEntity<List<UserStoryStatusDto>> getAllStatus(@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                List<UserStoryStatusDto> userStoryStatusDtos = userStoryStatusService.gatAll();
                if (!userStoryStatusDtos.isEmpty()){
                    return new ResponseEntity<>(userStoryStatusDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }
    @PostMapping("/savestatus")
    @Operation(description = "SAVE STATUS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "STATUS CREATE"),
            @ApiResponse(responseCode = "400",description = "FAIL STRUCT JSON")
    })
    public ResponseEntity<UserStoryStatusDto> saveStatus(@Valid @RequestBody UserStoryStatusDto userStoryStatusDto,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return new ResponseEntity<>(userStoryStatusService.saveStatus(userStoryStatusDto),HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @DeleteMapping("/deletestatus/{id}")
    @Operation(description = "DELETE STATUS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "Status Not Found")
    })
    public ResponseEntity deleteStatus(@PathVariable("id") Long idStatus,@RequestHeader(value="Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                if (userStoryStatusService.deleteProducto(idStatus)){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }
}
