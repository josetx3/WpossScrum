package com.wposs.scrum_back.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

public class TaskDto implements Serializable {
    @JsonProperty(value = "taskId",access = JsonProperty.Access.READ_ONLY)
    private UUID taskId;
    @JsonProperty(value = "nameTask")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String nameTask;

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
