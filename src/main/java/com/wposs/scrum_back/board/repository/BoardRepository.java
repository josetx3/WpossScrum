package com.wposs.scrum_back.board.repository;

import com.wposs.scrum_back.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    Boolean existsByTeamIdAndUserStoryIdAndTaskTeamIdAndEmployeeId(UUID idTeam,UUID idUserStory,UUID idTaskTeam,UUID idEmployee);
}
