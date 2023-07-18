package com.wposs.scrum_back.userstory.repository;

import com.wposs.scrum_back.area.entity.Area;
import com.wposs.scrum_back.userstory.entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, UUID> {
        List<UserStory> findBySubProjectId(UUID subProjectId);
        Boolean existsByUserStoryNameAndSubProjectId(String userStoryName,UUID idSubProject);

        UserStory getByUserStoryNameAndSubProjectId(String userStoryName,UUID idSubProject);
        @Query(value = "SELECT us.* FROM wposs.team te INNER JOIN wposs.sub_project sp ON te.team_id = sp.team_id\n" +
                "INNER JOIN wposs.user_story us ON us.sub_project_id = sp.sub_project_id\n" +
                "WHERE te.team_id=?1",nativeQuery = true)
        List<UserStory> getAllSserStoryToTeam(UUID tidTeam);

        @Query(value = "SELECT us.user_story_name, us.user_story_score, uss.user_story_status_name, sp.sub_project_name,\n" +
                "pro.project_name, ar.area_name, te.team_name\n" +
                "FROM wposs.user_story us INNER JOIN wposs.user_story_status uss \n" +
                "ON us.user_story_status_id = uss.user_story_status_id INNER JOIN wposs.sub_project sp\n" +
                "ON sp.sub_project_id = us.sub_project_id INNER JOIN wposs.project pro \n" +
                "ON sp.project_id = pro.project_id INNER JOIN wposs.area ar \n" +
                "ON ar.area_id = pro.area_id INNER JOIN wposs.team te ON te.area_id = ar.area_id \n" +
                "where te.team_id =?1 and ar.area_id = ?2 \n" +
                "and uss.user_story_status_name = 'REFINADA' or  uss.user_story_status_name = 'refinada' ;", nativeQuery = true)
        List<Object[]> getAllUserStoryRef(UUID idTeam, UUID idArea);

        @Query(value = "select user_story_code from wposs.user_story where sub_project_id=?1\n" +
                " order by user_story_code desc limit 1",nativeQuery = true)
        String getLastCodeUserStory(UUID subProjectId);
        /*@Query(value = "SELECT DISTINCT ust.* FROM   wposs.team te inner join wposs.board bo on  te.team_id =bo.fk_team\n" +
                "inner join wposs.user_story ust on  bo.fk_user_story=ust.user_story_id\n" +
                "inner join wposs.sprint_userstory sus on ust.user_story_id = sus.fk_user_story_id\n" +
                "where  te.team_id = ?1",nativeQuery = true)*/
        @Query(value="SELECT DISTINCT ust.* FROM   wposs.team te inner join wposs.sprint spri on  te.team_id =spri.fk_team_id\n" +
                "                inner join wposs.sprint_userstory sus on  spri.sprint_id=sus.fk_sprint_id\n" +
                "                inner join wposs.user_story ust on ust.user_story_id = sus.fk_user_story_id\n" +
                "                where  te.team_id = ?1 and spri.sprint_id=?2", nativeQuery = true)
        List<UserStory> getUserStoryByTeamAndSprint(UUID teamId,UUID sprintId);

        @Query(value = "SELECT distinct us.* FROM wposs.user_story  us INNER JOIN wposs.board bo ON us.user_story_id = bo.fk_user_story\n" +
                "INNER JOIN wposs.team te ON bo.fk_team = te.team_id\n" +
                "INNER JOIN wposs.area ar ON te.area_id = ar.area_id\n" +
                "INNER JOIN  wposs.sprint spr on ar.area_id = spr.fk_area_id\n" +
                "WHERE ar.area_id=?1 and te.team_id=?2 and spr.sprint_id=?3",nativeQuery = true)
        List<UserStory> getUserStoryByAreaAndTeamAndSprint(UUID areaId,UUID teamId,UUID sprintId);
}
