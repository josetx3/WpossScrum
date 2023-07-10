package com.wposs.scrum_back.client.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.client.dto.ClientDto;
import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.client.service.ClientServiceImpl;
import com.wposs.scrum_back.client.service.ClienteService;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/{id}")
    @Operation(summary = "Get client by String")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<ClientDto> findById(@PathVariable String id,@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                return clienteService.getClienteId(id).map(clientDto -> new ResponseEntity<>(clientDto,HttpStatus.OK)).orElse(null);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all clients")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ClientDto>> findAll(@RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                List<ClientDto> clients = clienteService.gatAllCliente();
                if (!clients.isEmpty()){
                    return new ResponseEntity<>(clients,HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/save/")
    @Operation(summary = "Create client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Client Created"),
            @ApiResponse(responseCode = "400",description = "client bad request")
    })
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto, BindingResult result, @RequestHeader(value="Authorization") String token){
        try{
            if(jwtUtil.getKey(token) != null) {
                try{
                    if (result.hasErrors()){
                        throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>(clienteService.saveCliente(clientDto),HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
           // System.out.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated client"),
            @ApiResponse(responseCode = "400",description = "Returns the data sent is invalid"),
            @ApiResponse(responseCode = "404",description = "Cliente Not Found")
    })
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable("id") String clientId,BindingResult result,@RequestHeader(value="Authorization") String token) {
        try{
            if(jwtUtil.getKey(token) != null) {
                try {
                    if (result.hasErrors()){
                        throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>(clienteService.updateCliente(clientId,clientDto),HttpStatus.OK);
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