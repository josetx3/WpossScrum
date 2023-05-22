package com.wposs.scrum_back.board.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.board.dto.BoardDto;
import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.board.service.BoardService;
import com.wposs.scrum_back.improvements.entity.Improvements;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/saveboard")
    @Operation(summary = "Save to Board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = ""),
            @ApiResponse(responseCode = "400",description = ""),
            @ApiResponse(responseCode = "500",description = "")
    })
    public ResponseEntity<BoardDto> saveBoard(@Valid @RequestBody BoardDto boardDto, BindingResult result){
        if (result.hasErrors()){
            throw new RequestException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(boardService.saveBoard(boardDto),HttpStatus.CREATED);
    }

    @GetMapping("/allboards")
    @Operation(summary = "Get All Boards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Boards Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Boards")
    })
    public ResponseEntity<List<BoardDto>> getAllBoards(){
        List<BoardDto> boardDtos = boardService.getAllBoards();
        if (!boardDtos.isEmpty()){
            return new ResponseEntity<>(boardDtos,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateboard/{idBoard}")
    @Operation(summary = "Update Board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<BoardDto> updateBoard(@PathVariable("idBoard")UUID boardId,@Valid @RequestBody BoardDto boardDto,BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>( boardService.updateBoard(boardId,boardDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteboard/{idBoard}")
    @Operation(summary = "Delecte Board To Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Not Contend Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Board")
    })
    public ResponseEntity deleteBoard(@PathVariable("idBoard")UUID boardId){
        if (boardService.deleteBoard(boardId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/boardid/{idboard}")
    @Operation(summary = "Get Board By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success Board"),
            @ApiResponse(responseCode = "404",description = "Not Fount Board")
    })
    public ResponseEntity<BoardDto> getBoardById(@PathVariable("idboard")UUID boardId){
        return boardService.getBoardById(boardId).map(boardDto -> new ResponseEntity<>(boardDto,HttpStatus.OK)).orElse(null);
    }
}
