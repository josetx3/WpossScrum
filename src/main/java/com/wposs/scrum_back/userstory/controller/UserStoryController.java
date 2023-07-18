package com.wposs.scrum_back.userstory.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.board.dto.BoardDto;
import com.wposs.scrum_back.sprint.dto.SprintDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import com.wposs.scrum_back.userstory.dto.UserStoryDtoRequest;
import com.wposs.scrum_back.userstory.entity.UserStory;
import com.wposs.scrum_back.userstory.repository.UserStoryRepository;
import com.wposs.scrum_back.userstory.service.UserStoryService;
import com.wposs.scrum_back.userstory.service.UserStoryServiceImpl;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@RestController
@RequestMapping("/userstory")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/{userStoryId}")
    @Operation(summary = "Get UserStory To Id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "successful search"),
            @ApiResponse(responseCode = "404",description = "Not Found UserStory")
    })
    @ApiResponse(responseCode = "200",description = "")
    public ResponseEntity<UserStoryDto> finById(@PathVariable("userStoryId") UUID userStoryId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            return userStoryService.getUserStoryById(userStoryId)
                    .map(userStoryDto -> new ResponseEntity<>(userStoryDto,HttpStatus.OK)).orElse(null);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/userstory/all")
    @Operation(summary = "Get all User Stories")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<UserStoryDto>> findAll(@RequestHeader(value="Authorization") String token){
       if (jwtUtil.validateToken(token)){
                List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStory();
                if(!userStoryDtos.isEmpty()){
                    return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/allByTeam/{teamId}/{sprintId}")
    @Operation(summary = "Get All Use Story By team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get All success"),
            @ApiResponse(responseCode = "404",description = "Not Found Users stories")
    })
    public ResponseEntity<List<UserStoryDto>> getAllUserStoryByTeam(@PathVariable("teamId") UUID teamId,@PathVariable("sprintId") UUID sprintId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryDto> userStoryDto = userStoryService.getAllUserStoryByTeam(teamId,sprintId);
            if(!userStoryDto.isEmpty()){
                return new ResponseEntity<>(userStoryDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/save")
    @Operation(summary = "Create User Story")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "user story created"),
            @ApiResponse(responseCode = "400",description = "user story bad request")
    })
    public ResponseEntity<UserStoryDto> create(@Valid @RequestBody UserStoryDto userStoryDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userStoryService.saveUserStory(userStoryDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the userStory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the updated User story"),
            @ApiResponse(responseCode = "400",description = "Story Not Found")
    })
    public ResponseEntity<UserStoryDto> updateUserStory(@Valid @RequestBody UserStoryDto userStoryDto, @PathVariable("id") UUID userStoryId,BindingResult result, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userStoryService.updateUserStory(userStoryId,userStoryDto),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/subproject/{subprojectId}")
    @Operation(summary = "Get all user stories by subproject id")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<UserStoryDto>> findAllUserStoriesBySubProjectId(@PathVariable("subprojectId") UUID subprojectId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStoryToSubProject(subprojectId);
            if (!userStoryDtos.isEmpty()){
                return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/userstoryteam/{id}")
    @Operation(summary = "Get All UserStory To Team")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<UserStoryDto>> getAllUserStoryToTeam(@PathVariable("id")UUID idTeam, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStoryToTeam(idTeam);
            if(userStoryDtos.isEmpty()){
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/userstoryteam/{idTeam}/{idArea}")
    @Operation(summary = "Get UserStory to team and area")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get all Success"),
            @ApiResponse(responseCode = "404",description = "Not Found")
    })
    public ResponseEntity<List<UserStoryDtoRequest>> getAllUserStoryRef(@PathVariable("idTeam") UUID idTeam, @PathVariable("idArea") UUID idArea, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryDtoRequest> userStoryDtoRequests = userStoryService.getAllUserStoryRef(idTeam, idArea);
            if (!userStoryDtoRequests.isEmpty()){
                return new ResponseEntity<>(userStoryDtoRequests,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/userFilter/{idarea}/{idteam}/{idsprint}")
    @Operation(summary = "Get Board By area, team and userhistory")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success user story"),
            @ApiResponse(responseCode = "404",description = "Not Fount user story")
    })
    public ResponseEntity<List<UserStoryDto>> getBoardByTeamAndAreaAndsprint(@PathVariable("idarea")UUID areaId, @PathVariable("idteam")UUID teamId, @PathVariable("idsprint")UUID sprintId, @RequestHeader(value="Authorization") String token){
        if (jwtUtil.validateToken(token)){
            List<UserStoryDto> userStoryDtos = userStoryService.getAllBoardsByTeamAndAreaAndSprint(areaId,teamId,sprintId);
            if (!userStoryDtos.isEmpty()){
                return new ResponseEntity<>(userStoryDtos,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
