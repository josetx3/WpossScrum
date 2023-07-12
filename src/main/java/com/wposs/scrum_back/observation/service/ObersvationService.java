package com.wposs.scrum_back.observation.service;

import com.wposs.scrum_back.observation.dto.ObservationDto;
import com.wposs.scrum_back.observation.entity.Observation;

import java.util.List;
import java.util.UUID;

public interface ObersvationService {
    List<ObservationDto> getAllObservation();
    ObservationDto saveObservation(ObservationDto observationDto);

   ObservationDto updateObservation(UUID idObservation, ObservationDto observationDto);
}
