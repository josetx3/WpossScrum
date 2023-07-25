package com.wposs.scrum_back.proposal.controller;

import com.wposs.scrum_back.proposal.dto.ProposalDto;
import com.wposs.scrum_back.proposal.service.ProposalService;
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
import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityScheme(
        name = "barerAuth",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("/proposal")
public class ProposalController {
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/all")
    @Operation(summary = "get all proposals")
    @SecurityRequirement(name = "barerAuth")
    @ApiResponse(responseCode = "200",description = "successfull search")
    public ResponseEntity<List<ProposalDto>> findAll(@RequestHeader(value = "Authorization")String token){
        if(jwtUtil.validateToken(token)){
            List<ProposalDto> proposalDtos=proposalService.getAllProposal();
            if (!proposalDtos.isEmpty()){
                return new ResponseEntity<>(proposalDtos,HttpStatus.OK);
            }
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping(value = "{id}")
    @Operation(summary = "Get proposal by id")
    @SecurityRequirement(name = "barerAuth")
    @ApiResponse(responseCode = "200",description = "successfull search")
    public ResponseEntity<ProposalDto> findById(@PathVariable("id") UUID proposalId,@RequestHeader(value = "Authorization") String token ){
        if(jwtUtil.validateToken(token)){
            return proposalService.getById(proposalId).map(proposalDto -> new ResponseEntity<>(proposalDto,HttpStatus.OK)).orElse(null);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/save")
    @Operation(summary = "save proposal")
    @SecurityRequirement(name="barerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "proposal created"),
            @ApiResponse(responseCode = "400", description = "proposal bad request")
    })
    public ResponseEntity<ProposalDto> create(@RequestBody ProposalDto proposalDto, BindingResult result, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" Se ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(proposalService.saveProposal(proposalDto),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}
