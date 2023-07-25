package com.wposs.scrum_back.proposal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposalDto {

    @JsonProperty(value = "proposalId",access =JsonProperty.Access.READ_ONLY)
    private UUID proposalId;

    @JsonProperty(value = "clientId")
    @NotNull(message = "el cliente no puede ser null")
    @NotEmpty
    @Pattern(regexp = "\\d+", message = "El campo cliente solo se admiten numeros")
    private String clientId;

    @JsonProperty(value = "subprojectId")
    @NotNull(message = "El subproyecto no puede ser nulo")
    private UUID subprojectId;

    @JsonProperty(value = "proposalName")
    @NotNull(message = "El nombre de la propuesta no puede ser null")
    @NotEmpty
    @Size(max = 60,message = "el nombre de la propuesta no puede exceder los 60 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\d ]+$",message = "el campo solo admite letras, numeros y espacios")
    private String proposalName;

    @JsonProperty(value = "proposalDescription")
    @NotNull(message = "la descripcion no puede ser null")
    @NotEmpty
    @Size(max = 300,message ="la descripcion no debe exceder los 300 caracteres")
    private String proposalDescription;

    @JsonProperty(value = "proposalArchive")

    private String proposalArchive;

    @JsonProperty(value = "proposalState")
    @NotNull(message = "el estado de la propuesta no puede ser null")
    @NotEmpty
    @Size(max = 20, message = "el tamaño de el estado no puede exceder los 20 cartacteres")
    private String proposalState;

    @JsonProperty(value = "clientName",access = JsonProperty.Access.READ_ONLY)
    private String clientName;

    @JsonProperty(value = "subProjectNameSubProjectName",access = JsonProperty.Access.READ_ONLY)
    private String subProjectNameSubProjectName;
    public UUID getProposalId() {
        return proposalId;
    }

    public void setProposalId(UUID proposalId) {
        this.proposalId = proposalId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public UUID getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(UUID subprojectId) {
        this.subprojectId = subprojectId;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public String getProposalDescription() {
        return proposalDescription;
    }

    public void setProposalDescription(String proposalDescription) {
        this.proposalDescription = proposalDescription;
    }

    public String getProposalArchive() {
        return proposalArchive;
    }

    public void setProposalArchive(String proposalArchive) {
        this.proposalArchive = proposalArchive;
    }

    public String getProposalState() {
        return proposalState;
    }

    public void setProposalState(String proposalState) {
        this.proposalState = proposalState;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSubProjectNameSubProjectName() {
        return subProjectNameSubProjectName;
    }

    public void setSubProjectNameSubProjectName(String subProjectNameSubProjectName) {
        this.subProjectNameSubProjectName = subProjectNameSubProjectName;
    }
}
