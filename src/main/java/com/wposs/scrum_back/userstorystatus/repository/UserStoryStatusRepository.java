package com.wposs.scrum_back.userstorystatus.repository;

import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import com.wposs.scrum_back.userstorystatus.entity.UserStoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryStatusRepository extends JpaRepository<UserStoryStatus,Long> {
    Boolean existsByUserStoryStateName(String stateName);


    UserStoryStatus findByUserStoryStateName(String stateName);
    @Query(value = "SELECT * FROM wposs.user_story_status",nativeQuery = true)
    List<UserStoryStatus> getAllStatus();
}
