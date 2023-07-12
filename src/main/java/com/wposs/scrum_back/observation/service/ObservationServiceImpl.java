package com.wposs.scrum_back.observation.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.observation.dto.ObservationDto;
import com.wposs.scrum_back.observation.entity.Observation;
import com.wposs.scrum_back.observation.repository.ObservationRepository;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ObservationServiceImpl implements ObersvationService{
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ObservationDto> getAllObservation() {
        return observationRepository.findAll().stream().map(observation -> {
            return modelMapper.map(observation,ObservationDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public ObservationDto saveObservation(ObservationDto observationDto) {
        Observation observation = modelMapper.map(observationDto,Observation.class);
        if (observationRepository.existsByObservationName(observation.getObservationName())){
            throw  new MessageGeneric("Ya existe un Registro con: "+observationDto.getObservationName(),"", HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(observationRepository.save(observation),ObservationDto.class);
        }catch (Exception ex){
            throw new InternalServerException("Error al Intentar Registrar la Observation","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ObservationDto updateObservation(UUID idObservation, ObservationDto observationDto) {
        if (observationRepository.existsByObservationName(observationDto.getObservationName())){
            throw  new MessageGeneric("Ya existe un Registro con: "+observationDto.getObservationName(),"", HttpStatus.CONFLICT);
        }
        return observationRepository.findById(idObservation).map(observation -> {
            observation.setObservationName((observationDto.getObservationName()!=null)?observationDto.getObservationName():observation.getObservationName());
            return modelMapper.map(observationRepository.save(observation), ObservationDto.class);
        }).orElseThrow(()->new MessageGeneric("No esta disponible la observacion a actualizar","404",HttpStatus.NOT_FOUND));
    }
}
