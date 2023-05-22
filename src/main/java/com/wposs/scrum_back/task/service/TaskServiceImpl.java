package com.wposs.scrum_back.task.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.task.dto.TaskDto;
import com.wposs.scrum_back.task.entity.Task;
import com.wposs.scrum_back.task.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<TaskDto> getAllTask() {
        return taskRepository.findAll().stream().map(task -> {
            return modelMapper.map(task, TaskDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDto saveTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto,Task.class);
        if (taskRepository.existsByNameTask(task.getNameTask())){
            throw new MessageGeneric("Ya existe una Tarea con este nombre: "+taskDto.getNameTask(),"409", HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(taskRepository.save(task), TaskDto.class);
        }catch (Exception ex){
            throw new InternalServerException("ERROR al intentar Registrar la Tarea","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
