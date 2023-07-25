package com.wposs.scrum_back.proposal.service;

import com.wposs.scrum_back.proposal.dto.ProposalDto;

import java.util.List;

public interface ProposalService {
    List<ProposalDto> getAllProposal();

    ProposalDto saveProposal(ProposalDto proposalDto);
}
