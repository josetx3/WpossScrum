package com.wposs.scrum_back.proposal.entity;

import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.subProject.entity.SubProject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "proposal",schema = "wposs")
public class Proposal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "proposal_id")
    private UUID proposalId;

    @Column(name = "fk_client_id")
    private String clientId;

    @Column(name = "fk_subproject_id")
    private UUID subprojectId;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_client_id", insertable = false, updatable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fk_subproject_id", insertable = false, updatable = false)
    private SubProject subProject;

    @Column(name = "proposal_name", nullable = false,length = 60)
    private String proposalName;

    @Column(name = "proposal_description", nullable = false,length = 300)
    private String proposalDescription;


    @Column(name = "proposal_archive", columnDefinition = "text")
    private String proposalArchive;

    @Column(name = "proposal_state", nullable = false,length = 20)
    private String proposalState;

    public UUID getProposalId() {
        return proposalId;
    }

    public void setProposalId(UUID proposalId) {
        this.proposalId = proposalId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public UUID getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(UUID subprojectId) {
        this.subprojectId = subprojectId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
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
}