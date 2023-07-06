package com.wposs.scrum_back.userstory.repository;

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
                "and uss.user_story_status_name = 'REFINADA';", nativeQuery = true)
        List<Object[]> getAllUserStoryRef(UUID idTeam, UUID idArea);
}
