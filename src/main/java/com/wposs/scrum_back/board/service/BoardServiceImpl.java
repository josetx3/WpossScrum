package com.wposs.scrum_back.board.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.board.dto.BoardDto;
import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.board.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public BoardDto saveBoard(BoardDto boardDto) {
        Board board = modelMapper.map(boardDto,Board.class);
        if (boardRepository.existsByTeamIdAndUserStoryIdAndTaskTeamIdAndEmployeeId(board.getTeamId(),board.getUserStoryId(),board.getTaskTeamId(),board.getEmployeeId())){
            throw new MessageGeneric("Ya existe un tablero con la infomacion ingresada","409", HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(boardRepository.save(board),BoardDto.class);
        }catch (Exception ex){
            throw new InternalServerException("error en el servidor,JSON mal estructurado","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream().map(board -> {
            return modelMapper.map(board,BoardDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public BoardDto updateBoard(UUID boardId, BoardDto boardDto) {
        return boardRepository.findById(boardId).map(board -> {
            board.setDate((boardDto.getDate()!=null)?boardDto.getDate():board.getDate());
            board.setTeamId((boardDto.getTeamId()!=null)?boardDto.getTeamId():board.getTeamId());
            board.setUserStoryId((boardDto.getUserStoryId()!=null)?boardDto.getUserStoryId():board.getUserStoryId());
            board.setTaskTeamId((boardDto.getTaskTeamId()!=null)?boardDto.getTaskTeamId():board.getTaskTeamId());
            board.setEmployeeId((boardDto.getEmployeeId()!=null)?boardDto.getEmployeeId():board.getEmployeeId());
            return modelMapper.map(boardRepository.save(board),BoardDto.class);
        }).orElseThrow(()->new MessageGeneric("no esta disponioble el tablero a Actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public Boolean deleteBoard(UUID boardId) {
        if(boardRepository.findById(boardId).isPresent()){
            boardRepository.deleteById(boardId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BoardDto> getBoardById(UUID boardId) {
        return Optional.ofNullable(boardRepository.findById(boardId)
                .map(board -> {
                    return modelMapper.map(board,BoardDto.class);
                }).orElseThrow(()->new MessageGeneric("el Tablero solicitado no se encentra Registrado","404",HttpStatus.NOT_FOUND)));
    }
}
