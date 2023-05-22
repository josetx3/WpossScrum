package com.wposs.scrum_back.task.service;

import com.wposs.scrum_back.task.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTask();
    TaskDto saveTask(TaskDto taskDto);
}
