package com.wposs.scrum_back.area.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.area.service.AreaServiceImpl;
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
import java.util.*;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaServiceImpl areaService;

    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping("/{id}")
    @Operation(summary = "Get Area to Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Area Success")
    public ResponseEntity<AreaDto> findById(@PathVariable UUID id, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            return areaService.getAreaId(id).map(areaDto -> new ResponseEntity<>(areaDto, HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/employee/{idEmployee}")
    @Operation(summary = "Get all areas bye employee")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Get areas by employe succes"),
            @ApiResponse(responseCode = "404", description = "Areas not found")
    })
    public ResponseEntity<List<AreaDto>> getAllAreasByEmployee(@PathVariable("idEmployee") UUID idEmployee,@RequestHeader(value = "Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<AreaDto> areaDtos= areaService.getAllAreaByIdEmployee(idEmployee);
            if (!areaDtos.isEmpty()){
                return new ResponseEntity<>(areaDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/all")
    @Operation(summary = "Get all areas")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Get All List Success")
    public ResponseEntity<List<AreaDto>> findAll(@RequestHeader(value="Authorization") String token) {
        if (token != null) {
            if (token.startsWith("Bearer ")) {
                token=token.substring(7);
            }
        }
        if (jwtUtil.validateToken(token)){
            List<AreaDto> areaDtos = areaService.getAllArea();
            if (!areaDtos.isEmpty()) {
                return new ResponseEntity<>(areaDtos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save/")
    @Operation(summary = "Create Area")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created Area"),
            @ApiResponse(responseCode = "400", description = "Area Bad Request")
    })
    public ResponseEntity<AreaDto> create(@Valid @RequestBody AreaDto areaDto, BindingResult result, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            if(result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(areaService.saveArea(areaDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the area")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the updated area"),
            @ApiResponse(responseCode = "404", description = "Area Not Found")
    })
    public ResponseEntity<AreaDto> updateArea(@RequestBody AreaDto areaDto, @PathVariable("id") UUID areaId,BindingResult result, @RequestHeader(value="Authorization") String token) {
    if (jwtUtil.validateToken(token)){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted a ingresado: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(areaService.updateArea(areaId, areaDto), HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    }

    @DeleteMapping("/deletearea/{id}")
    @Operation(description = "DELETE AREA TO ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "DELETE SUCCESS"),
            @ApiResponse(responseCode = "404",description = "AREA NOT FOUND")
    })
    public ResponseEntity deleteArea(@PathVariable("id")UUID idArea, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            if (areaService.deleteArea(idArea)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
