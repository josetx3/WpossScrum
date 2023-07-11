package com.wposs.scrum_back.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    @JsonProperty(value = "clientNit")
    @NotNull(message = "El NIT del cliente no puede ser null")
    @NotEmpty
    @Size(max = 20,message = "El limite de NIT es de 20 caraters")
    @Pattern(regexp = "\\d+",message = "El NIT solo se admite numeros")
    private String clientId;

    @JsonProperty(value = "client_name")
    @NotNull(message = "El campo nombreCliente no puede ser null")
    @NotEmpty
    @Size(max = 20,message = "El campo nombreCliente debe tener menos de 20 carater")
    @Pattern(regexp = "^[a-zA-Z \\d]+$",message = "El campo nombreCliente solo puede contener letras numeros y espacios")
    private String clientName;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
