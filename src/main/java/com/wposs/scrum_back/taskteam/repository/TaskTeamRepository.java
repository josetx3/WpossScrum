package com.wposs.scrum_back.taskteam.repository;

import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskTeamRepository extends JpaRepository<TaskTeam, UUID> {
    @Query(value = "SELECT * FROM wposs.task_team",nativeQuery = true)
    List<TaskTeam> getAllTaskTeam();
    List<TaskTeam> getByTeamId(UUID teamId);
    Boolean existsByTaskNameAndTeamId(String taskTeamName,UUID teamId);
}
