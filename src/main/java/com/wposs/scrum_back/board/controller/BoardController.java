package com.wposs.scrum_back.board.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.board.dto.BoardDto;
import com.wposs.scrum_back.board.service.BoardService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private JWTUtil jwtUtil;
    @PostMapping("/saveboard")
    @Operation(summary = "Save to Board")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = ""),
            @ApiResponse(responseCode = "400",description = ""),
            @ApiResponse(responseCode = "500",description = "")
    })
    public ResponseEntity<BoardDto> saveBoard(@Valid @RequestBody BoardDto boardDto, BindingResult result, @RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            if (result.hasErrors()) {
                throw new RequestException("error en estructura de JSON " + result.getFieldError().getRejectedValue(), "400", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(boardService.saveBoard(boardDto), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/allboards")
    @Operation(summary = "Get All Boards")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Boards Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Boards")
    })
    public ResponseEntity<List<BoardDto>> getAllBoards(@RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           List<BoardDto> boardDtos = boardService.getAllBoards();
           if (!boardDtos.isEmpty()){
               return new ResponseEntity<>(boardDtos,HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
    }

    @PutMapping("/updateboard/{idBoard}")
    @Operation(summary = "Update Board")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<BoardDto> updateBoard(@PathVariable("idBoard")UUID boardId,@Valid @RequestBody BoardDto boardDto,BindingResult result, @RequestHeader(value="Authorization") String token){
      if (jwtUtil.validateToken(token)){
          if (result.hasErrors()){
              throw new MethodArgumentNotValidException("error en estructura de JSON "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>( boardService.updateBoard(boardId,boardDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deleteboard/{idBoard}")
    @Operation(summary = "Delecte Board To Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Not Contend Success"),
            @ApiResponse(responseCode = "404",description = "Not Found Board")
    })
    public ResponseEntity deleteBoard(@PathVariable("idBoard")UUID boardId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
                if (boardService.deleteBoard(boardId)){
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/boardid/{idboard}")
    @Operation(summary = "Get Board By Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success Board"),
            @ApiResponse(responseCode = "404",description = "Not Fount Board")
    })
    public ResponseEntity<BoardDto> getBoardById(@PathVariable("idboard")UUID boardId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return boardService.getBoardById(boardId).map(boardDto -> new ResponseEntity<>(boardDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/boardFilter/{idarea}/{idteam}/{iduserhistory}")
    @Operation(summary = "Get Board By area, team and userhistory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success Board"),
            @ApiResponse(responseCode = "404",description = "Not Fount Board")
    })
    public ResponseEntity<List<BoardDto>> getBoardByTeamAndAreaAndUserStory(@PathVariable("idarea")UUID areaId,@PathVariable("idteam")UUID teamId,@PathVariable("iduserhistory")UUID userStoryId, @RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
           List<BoardDto> boardDtos = boardService.getAllBoardsByTeamAndAreaAndUserStory(areaId,teamId,userStoryId);
           if (!boardDtos.isEmpty()){
               return new ResponseEntity<>(boardDtos,HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
