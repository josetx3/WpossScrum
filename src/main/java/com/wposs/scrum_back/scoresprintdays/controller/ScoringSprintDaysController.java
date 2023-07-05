package com.wposs.scrum_back.scoresprintdays.controller;
import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.scoresprintdays.dto.ScoringSprintDaysDto;
import com.wposs.scrum_back.scoresprintdays.entity.ScoringSprintDays;
import com.wposs.scrum_back.scoresprintdays.service.ScoringSprintDaysService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/scoringSpring")
public class ScoringSprintDaysController {

    @Autowired
    private ScoringSprintDaysService scoringSprintDaysService;

    @Autowired
    private JWTUtil jwtUtil;

    @PutMapping("/update_score/{isSprint}")
    @Operation(summary = "Update score sprint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Save Success"),
            @ApiResponse(responseCode = "500",description = "An internal error occurred")
    })
    public ResponseEntity<ScoringSprintDaysDto> updateScore(@PathVariable("isSprint") UUID idSprint, @RequestBody  ScoringSprintDaysDto scoringSprintDaysDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                try {
                    if (result.hasErrors()){
                        throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400", HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>(scoringSprintDaysService.updateScoreSpring(idSprint,scoringSprintDaysDto),HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }
}
