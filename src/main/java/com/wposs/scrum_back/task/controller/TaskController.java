package com.wposs.scrum_back.task.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.task.dto.TaskDto;
import com.wposs.scrum_back.task.service.TaskService;
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

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/all")
    @Operation(summary = "Get All Task")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Succes"),
            @ApiResponse(responseCode = "404",description = "Not Found Task")
    })
    public ResponseEntity<List<TaskDto>> getAll(@RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
                List<TaskDto> taskDtos = taskService.getAllTask();
                if (!taskDtos.isEmpty()) {
                    return new ResponseEntity<>(taskDtos, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/savetask")
    @Operation(summary = "Save To Task")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "400",description = "JSON mal estructurtado")
    })
    public ResponseEntity<TaskDto> saveTask(@Valid @RequestBody TaskDto taskDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (result.hasErrors()){
                    throw new MethodArgumentNotValidException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(taskService.saveTask(taskDto),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
