package com.wposs.scrum_back.proposal.service;

import com.wposs.scrum_back.proposal.dto.ProposalDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProposalService {
    List<ProposalDto> getAllProposal();
    Optional<ProposalDto> getById(UUID proposalId);
    ProposalDto saveProposal(ProposalDto proposalDto);
}
