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
    TaskTeam getByTaskNameAndTeamId(String taskTeamName,UUID teamId);

    @Query(value = "SELECT tte.task_team_name, tte.task_team_hours, sp.numero_sprint FROM wposs.sprint sp inner join wposs.team te on sp.fk_team_id=te.team_id\n" +
            " inner join wposs.task_team tte on  te.team_id=tte.fk_team\n" +
            "inner join wposs.board bo on  tte.task_team_id =bo.fk_task_team \n" +
            "inner join wposs.user_story ust on  bo.fk_user_story=ust.user_story_id \n" +
            "inner join wposs.sprint_userstory sus on ust.user_story_id = sus.fk_user_story_id and sp.sprint_id = sus.fk_sprint_id\n" +
            "where te.team_id=?1 and ust.user_story_id=?2",nativeQuery = true)
    List<Object[]> getDataTaskByTeamAndUserStory(UUID teamId, UUID userStoryId);


    @Query(value = "SELECT distinct tte.* from wposs.user_story us inner join wposs.task_team tte on us.user_story_id = tte.fk_user_story \n" +
            "inner join wposs.team te on tte.fk_team = te.team_id \n" +
            "inner join wposs.sprint sp on te.team_id = sp.fk_team_id \n" +
            "inner join wposs.sprint_userstory spu on sp.sprint_id = spu.fk_sprint_id and us.user_story_id = spu.fk_user_story_id\n" +
            "where te.team_id=?1 and us.user_story_id=?2",nativeQuery = true)
    List<TaskTeam> getTaskTeamByTeamAndUserStory(UUID teamId, UUID userStoryId);
}
