package com.wposs.scrum_back.task.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.task.dto.TaskDto;
import com.wposs.scrum_back.task.service.TaskService;
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

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    @Operation(summary = "Get All Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All Succes"),
            @ApiResponse(responseCode = "404",description = "Not Found Task")
    })
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> taskDtos = taskService.getAllTask();
        if (!taskDtos.isEmpty()) {
            return new ResponseEntity<>(taskDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/savetask")
    @Operation(summary = "Save To Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "400",description = "JSON mal estructurtado")
    })
    public ResponseEntity<TaskDto> saveTask(@Valid @RequestBody TaskDto taskDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskService.saveTask(taskDto),HttpStatus.CREATED);
    }
}
