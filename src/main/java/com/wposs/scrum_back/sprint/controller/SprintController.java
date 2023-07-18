package com.wposs.scrum_back.sprint.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprint.dto.SprintDtoRequest;
import com.wposs.scrum_back.sprint.service.SprintService;
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
@RequestMapping("/sprint")
public class SprintController {
    @Autowired
    private SprintService sprintService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/all")
    @Operation(summary = "Get All Sprint")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All success"),
            @ApiResponse(responseCode = "404",description = "Not Found Sprint")
    })
    public ResponseEntity<List<SprintDto>> getAllSprint(@RequestHeader(value="Authorization") String token){
      if (jwtUtil.validateToken(token)){
          List<SprintDto> sprintDtos = sprintService.getAllSprint();
          if(!sprintDtos.isEmpty()){
              return new ResponseEntity<>(sprintDtos, HttpStatus.OK);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

      }else{
          return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    }

    @PostMapping("/savesprint")
    @Operation(summary = "Save Sprint")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Create Success"),
            @ApiResponse(responseCode = "400",description = "Sprint Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server")
    })
    public ResponseEntity<SprintDto> saveSprint(@Valid @RequestBody SprintDto sprintDto, BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException("error mal estructura en el JSON","400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(sprintService.saveSprint(sprintDto),HttpStatus.CREATED);

        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



    @GetMapping("/sprint/{idsprint}")
    @Operation(summary = "Get Sprint By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Succes Sprint"),
            @ApiResponse(responseCode = "404",description = "Sprint Not Found")
    })
    public ResponseEntity<SprintDto> getSprintByid(@PathVariable("idsprint") UUID idSprint,@RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
                return sprintService.sprintById(idSprint).map(sprintDto -> new ResponseEntity<>(sprintDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/sprintData/{idsprint}")
    @Operation(summary = "Get Sprint data By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Succes Sprint"),
            @ApiResponse(responseCode = "404",description = "Sprint data Not Found")
    })
    public ResponseEntity<SprintDtoRequest> getDataSprint(@PathVariable("idsprint") UUID idSprint, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           return new ResponseEntity<>(sprintService.getDataSprint(idSprint),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updatesprint/{idsprint}")
    @Operation(summary = "Update Sprint")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Success Sprint"),
            @ApiResponse(responseCode = "400",description = "Data Invaild"),
            @ApiResponse(responseCode = "404",description = "Sprint Not Found")
    })
    public ResponseEntity<SprintDto> updateSprint(@PathVariable("idsprint")UUID idSprint,@Valid @RequestBody SprintDto sprintDto,BindingResult result,@RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (result.hasErrors()){
                    throw new MethodArgumentNotValidException("Data no Valida: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(sprintService.updateSprint(idSprint,sprintDto),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/sprinteam/{idTeam}")
    @Operation(summary = "Get Sprint data By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Succes Sprint by Team"),
            @ApiResponse(responseCode = "404",description = "Sprint  Not Found")
    })
    public ResponseEntity<List<SprintDto>> getSprintByTeam(@PathVariable("idTeam") UUID idTeam, @RequestHeader(value="Authorization") String token){
        if(jwtUtil.validateToken(token)){
            List<SprintDto> sprintDtos= sprintService.getSprintByTeam(idTeam);
            if (!sprintDtos.isEmpty()){
                return new ResponseEntity<>(sprintDtos, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
