package com.wposs.scrum_back.observation.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.observation.dto.ObservationDto;
import com.wposs.scrum_back.observation.service.ObersvationService;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
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
@RequestMapping("/observation")
public class ObservationController {
    @Autowired
    private ObersvationService obersvationService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/all")
    @Operation(summary = "Get All Observation")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Success"),
            @ApiResponse(responseCode = "400",description = "")
    })
    public ResponseEntity<List<ObservationDto>> getAllObservation(@RequestHeader(value="Authorization") String token) {
      if (jwtUtil.validateToken(token)){
          List<ObservationDto> observationDtos = obersvationService.getAllObservation();
          if (!observationDtos.isEmpty()) {
              return new ResponseEntity<>(observationDtos, HttpStatus.OK);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      }else{
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    @PostMapping("/saveobservation")
    @Operation(summary = "Save Observation")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "400",description = "Error JSON")
    })
    public ResponseEntity<ObservationDto> saveObservation(@Valid @RequestBody ObservationDto observationDto, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (result.hasErrors()){
                    throw new MethodArgumentNotValidException("ERROR en el JSON: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(obersvationService.saveObservation(observationDto),HttpStatus.CREATED);
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
        public ResponseEntity<ObservationDto> updateObservation(@RequestBody ObservationDto observationDto, @PathVariable("id") UUID idObservations, BindingResult result, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(obersvationService.updateObservation(idObservations,observationDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
