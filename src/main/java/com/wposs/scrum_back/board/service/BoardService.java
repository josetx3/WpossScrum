package com.wposs.scrum_back.board.service;

import com.wposs.scrum_back.board.dto.BoardDto;
import com.wposs.scrum_back.board.entity.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardService {
    BoardDto saveBoard(BoardDto boardDto);
    List<BoardDto> getAllBoards();
    BoardDto updateBoard(UUID boardId,BoardDto boardDto);
    Boolean deleteBoard(UUID boardId);
    Optional<BoardDto> getBoardById(UUID boardId);
}
