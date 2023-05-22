package com.wposs.scrum_back.task.repository;

import com.wposs.scrum_back.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Boolean existsByNameTask(String nameTask);
}
