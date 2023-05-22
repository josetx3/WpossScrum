package com.wposs.scrum_back.client.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.client.dto.ClientDto;
import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.client.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClienteService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClientDto> gatAllCliente() {
        return clientRepository.findAll().stream().map(client -> {
            return modelMapper.map(client,ClientDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> getClienteId(String idCliente) {
        return Optional.ofNullable(clientRepository.findById(idCliente).map(client -> {
            return modelMapper.map(client,ClientDto.class);
        }).orElseThrow(()->new MessageGeneric("El cliente Solicitado no se encuentra registrado","400", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ClientDto saveCliente(ClientDto clientDto) {
        if(clientRepository.findById(clientDto.getClientId()).isPresent()){
            throw new MessageGeneric("Ya se encuentra Un cliente Registrado con este NIT: "+clientDto.getClientId(),"409",HttpStatus.CONFLICT);
        }
        try{
            return modelMapper.map(clientRepository.save(modelMapper.map(clientDto,Client.class)),ClientDto.class);
        }catch (Exception ex){
            throw new RequestException("Ha surjido un error inesperado,JSON mal estructurado","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ClientDto updateCliente(String idCliente, ClientDto clientDto) {
        return clientRepository.findById(idCliente).map(client -> {
            client.setClientName((clientDto.getClientName()!=null)?clientDto.getClientName():client.getClientName());
            return modelMapper.map(clientRepository.save(client),ClientDto.class);
        }).orElseThrow(()->new MessageGeneric("No se encontro el cliente a Actualizar","400",HttpStatus.NOT_FOUND));
    }

}