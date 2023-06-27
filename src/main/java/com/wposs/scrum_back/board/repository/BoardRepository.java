package com.wposs.scrum_back.board.repository;

import com.wposs.scrum_back.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    Boolean existsByTeamIdAndUserStoryIdAndTaskTeamIdAndEmployeeId(UUID idTeam,UUID idUserStory,UUID idTaskTeam,UUID idEmployee);

    @Query(value = "SELECT bo.* FROM wposs.user_story  us INNER JOIN wposs.board bo ON us.user_story_id = bo.fk_user_story\n" +
            "INNER JOIN wposs.team te ON bo.fk_team = te.team_id INNER JOIN wposs.area ar ON te.area_id = ar.area_id\n" +
            "WHERE ar.area_id=?1 and te.team_id=?2 and us.user_story_id=?3",nativeQuery=true)
    List<Board> getAllBoardsByTeamAndAreaAndUserStory(UUID areaId, UUID teamId, UUID userStoryId);
}
