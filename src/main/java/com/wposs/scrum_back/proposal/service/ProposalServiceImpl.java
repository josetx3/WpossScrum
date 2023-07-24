package com.wposs.scrum_back.proposal.service;

import com.wposs.scrum_back.proposal.entity.Proposal;
import com.wposs.scrum_back.proposal.repository.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalServiceImpl implements ProposalService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProposalRepository proposalRepository;
}
