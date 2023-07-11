package com.wposs.scrum_back.scoresprintdays.repository;

import com.wposs.scrum_back.scoresprintdays.entity.ScoringSprintDays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScoringSprintsDaysRepository extends JpaRepository<ScoringSprintDays, UUID> {
    Boolean existsBySprintId(UUID sprintId);
    Optional<ScoringSprintDays>  findBySprintId(UUID sprintId);

}
