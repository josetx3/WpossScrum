package com.wposs.scrum_back.scoresprintdays.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
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
                return modelMapper.map(scoringSprintsDaysRepository.save(scoringSprintDays),ScoringSprintDaysDto.class);
            }).orElseThrow(()->new MessageGeneric("No se encontro el cliente a Actualizar","400",HttpStatus.NOT_FOUND));
        }else{
            ScoringSprintDays scoringSprintDays=modelMapper.map(scoringSprintDaysDto, ScoringSprintDays.class);
            return modelMapper.map(scoringSprintsDaysRepository.save(scoringSprintDays), ScoringSprintDaysDto.class);
        }
    }
}
