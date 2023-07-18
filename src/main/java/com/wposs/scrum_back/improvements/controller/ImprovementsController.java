package com.wposs.scrum_back.improvements.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.improvements.dto.ImprovementsDto;
import com.wposs.scrum_back.improvements.entity.Improvements;
import com.wposs.scrum_back.improvements.service.ImprovementsService;
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
@RequestMapping("/improvements")
public class ImprovementsController {
    @Autowired
    private ImprovementsService improvementsService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/all")
    @Operation(summary = "Get All Improvements")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Improvements")
    })
    public ResponseEntity<List<ImprovementsDto>> getAllImprovements(@RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            List<ImprovementsDto> improvementsDtos = improvementsService.getAllImprovements();
            if (!improvementsDtos.isEmpty()){
                return new ResponseEntity<>(improvementsDtos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/saveimprovements")
    @Operation(summary = "Save Improvements")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Improvements Success"),
            @ApiResponse(responseCode = "400",description = "JSON Bad Request")
    })
    public ResponseEntity<ImprovementsDto> saveImprovements(@Valid @RequestBody ImprovementsDto improvementsDto, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(improvementsService.saveImprovements(improvementsDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/improvementsId/{id}")
    @Operation(summary = "Get Improvements By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Improvements Success"),
            @ApiResponse(responseCode = "404",description = "Improvements Not Found")
    })
    public ResponseEntity<ImprovementsDto> getByIdImprovements(@PathVariable("id")UUID idImprovemets,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return improvementsService.getByIdimprovements(idImprovemets)
                    .map(improvementsDto -> new ResponseEntity<>(improvementsDto,HttpStatus.OK))
                    .orElse(null);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deleteimprovements/{idimprovements}")
    @Operation(summary = "Delete Improvements")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Delete Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Improvements")
    })
    public ResponseEntity deleteImprovements(@PathVariable("idimprovements") UUID idImprovements,@RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           if (improvementsService.deleteImprovements(idImprovements)){
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
