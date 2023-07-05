package com.wposs.scrum_back.scoresprintdays.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.client.dto.ClientDto;
import com.wposs.scrum_back.client.entity.Client;
import com.wposs.scrum_back.scoresprintdays.dto.ScoringSprintDaysDto;
import com.wposs.scrum_back.scoresprintdays.entity.ScoringSprintDays;
import com.wposs.scrum_back.scoresprintdays.repository.ScoringSprintsDaysRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScoringSprintDaysServiceImpl implements ScoringSprintDaysService  {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ScoringSprintsDaysRepository scoringSprintsDaysRepository;
    @Override
    public ScoringSprintDaysDto updateScoreSpring(UUID idSprint, ScoringSprintDaysDto scoringSprintDaysDto) {

        if(scoringSprintsDaysRepository.existsBySprintId(idSprint)){

            return scoringSprintsDaysRepository.findBySprintId(idSprint).map(scoringSprintDays -> {
                scoringSprintDays.setScoreSprint((scoringSprintDaysDto.getScoreSprint()!=null)?scoringSprintDaysDto.getScoreSprint(): scoringSprintDays.getScoreSprint());
                scoringSprintDays.setDate((scoringSprintDaysDto.getDate()!=null)?scoringSprintDaysDto.getDate():scoringSprintDays.getDate());
                return modelMapper.map(scoringSprintsDaysRepository.save(scoringSprintDays),ScoringSprintDaysDto.class);
            }).orElseThrow(()->new MessageGeneric("No se encontro el cliente a Actualizar","400",HttpStatus.NOT_FOUND));
        }else{
            try{
                return modelMapper.map(scoringSprintsDaysRepository.save(modelMapper.map(scoringSprintDaysDto, ScoringSprintDays.class)), ScoringSprintDaysDto.class);
            }catch (Exception ex){
                throw new RequestException("Ha surjido un error inesperado,JSON mal estructurado","500", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
