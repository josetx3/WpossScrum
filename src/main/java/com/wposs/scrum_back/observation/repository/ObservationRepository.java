package com.wposs.scrum_back.observation.repository;

import com.wposs.scrum_back.observation.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, UUID> {
    Boolean existsByObservationName(String observationName);
}
