package com.wposs.scrum_back.client.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.client.dto.ClientDto;
import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.client.service.ClientServiceImpl;
import com.wposs.scrum_back.client.service.ClienteService;
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

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    @Operation(summary = "Get client by String")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<ClientDto> findById(@PathVariable String id){
        return clienteService.getClienteId(id).map(clientDto -> new ResponseEntity<>(clientDto,HttpStatus.OK)).orElse(null);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all clients")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ClientDto>> findAll(){
        List<ClientDto> clients = clienteService.gatAllCliente();
        if (!clients.isEmpty()){
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save/")
    @Operation(summary = "Create client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Client Created"),
            @ApiResponse(responseCode = "400",description = "client bad request")
    })
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto, BindingResult result){
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clienteService.saveCliente(clientDto),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated client"),
            @ApiResponse(responseCode = "400",description = "Returns the data sent is invalid"),
            @ApiResponse(responseCode = "404",description = "Cliente Not Found")
    })
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable("id") String clientId,BindingResult result) {
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clienteService.updateCliente(clientId,clientDto),HttpStatus.OK);
    }

}