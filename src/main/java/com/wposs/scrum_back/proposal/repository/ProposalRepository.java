package com.wposs.scrum_back.proposal.repository;

import com.wposs.scrum_back.proposal.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalRepository extends JpaRepository< Proposal,UUID> {
}
