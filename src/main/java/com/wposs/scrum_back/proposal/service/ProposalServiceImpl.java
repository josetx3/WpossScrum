package com.wposs.scrum_back.proposal.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.proposal.dto.ProposalDto;
import com.wposs.scrum_back.proposal.entity.Proposal;
import com.wposs.scrum_back.proposal.repository.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public List<ProposalDto> getAllProposal() {
        return proposalRepository.findAll().stream().map(proposal -> {
            return  modelMapper.map(proposal, ProposalDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public ProposalDto saveProposal(ProposalDto proposalDto) {
       Proposal proposal= modelMapper.map(proposalDto,Proposal.class);
       try{
           return modelMapper.map(proposalRepository.save(proposal),ProposalDto.class);
       }catch (Exception e){
           throw  new InternalServerException("Error al crear una nueva propuesta,json mal estructurado","500", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
