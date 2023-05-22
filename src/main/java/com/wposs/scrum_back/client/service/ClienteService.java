package com.wposs.scrum_back.client.service;

import com.wposs.scrum_back.client.dto.ClientDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {
    List<ClientDto> gatAllCliente();

    Optional<ClientDto> getClienteId(String idCliente);

    ClientDto saveCliente(ClientDto clientDto);

    ClientDto updateCliente(String idCliente, ClientDto clientDto);
}

