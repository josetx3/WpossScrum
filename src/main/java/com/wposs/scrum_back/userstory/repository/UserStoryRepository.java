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
}
