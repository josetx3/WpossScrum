package com.wposs.scrum_back.client.controller;

import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.client.dto.ClientDto;
import com.wposs.scrum_back.client.service.ClienteService;
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

import javax.validation.Valid;;
import java.util.List;
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/{id}")
    @Operation(summary = "Get client by String")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<ClientDto> findById(@PathVariable String id,@RequestHeader(value="Authorization") String token){
        //Condicion para validar el token de jwt
        if(jwtUtil.validateToken(token)){
           return clienteService.getClienteId(id).map(clientDto -> new ResponseEntity<>(clientDto,HttpStatus.OK)).orElse(null);
       }else{//Cuando el token no es valido retorna un UNAUTHORIZED
       return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all clients")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200",description = "successful search")
    public ResponseEntity<List<ClientDto>> findAll(@RequestHeader(value="Authorization") String token){
         if(jwtUtil.validateToken(token)){
             List<ClientDto> clients = clienteService.gatAllCliente();
             if (!clients.isEmpty()){
                 return new ResponseEntity<>(clients,HttpStatus.OK);
             }
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }else{
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PostMapping("/save/")
    @Operation(summary = "Create client")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Client Created"),
            @ApiResponse(responseCode = "400",description = "client bad request")
    })
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto, BindingResult result, @RequestHeader(value="Authorization") String token){
         if (jwtUtil.validateToken(token)){
             if (result.hasErrors()){
                 throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
             }
             return new ResponseEntity<>(clienteService.saveCliente(clientDto),HttpStatus.OK);
         } else{
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the client")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Return the updated client"),
            @ApiResponse(responseCode = "400",description = "Returns the data sent is invalid"),
            @ApiResponse(responseCode = "404",description = "Cliente Not Found")
    })
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable("id") String clientId,BindingResult result,@RequestHeader(value="Authorization") String token) {
        if (jwtUtil.validateToken(token)){
            if (result.hasErrors()){
                throw new MethodArgumentNotValidException(result.getFieldError().getDefaultMessage()+" usted ingreso: "+result.getFieldError().getRejectedValue(),"400",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(clienteService.updateCliente(clientId,clientDto),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}