package com.wposs.scrum_back.observation.service;

import com.wposs.scrum_back.observation.dto.ObservationDto;
import com.wposs.scrum_back.observation.entity.Observation;

import java.util.List;

public interface ObersvationService {
    List<ObservationDto> getAllObservation();
    ObservationDto saveObservation(ObservationDto observationDto);
}
