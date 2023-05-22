package com.wposs.scrum_back.improvements.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.improvements.dto.ImprovementsDto;
import com.wposs.scrum_back.improvements.entity.Improvements;
import com.wposs.scrum_back.improvements.repository.ImprovementsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImprovementsServiceImpl implements ImprovementsService{
    @Autowired
    private ImprovementsRepository improvementsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ImprovementsDto> getAllImprovements() {
        return improvementsRepository.findAll().stream().map(improvements -> {
            return modelMapper.map(improvements,ImprovementsDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public ImprovementsDto saveImprovements(ImprovementsDto improvementsDto) {
        Improvements improvements = modelMapper.map(improvementsDto,Improvements.class);
        try {
            return modelMapper.map(improvementsRepository.save(improvements),ImprovementsDto.class);
        }catch (Exception ex){
            throw new MessageGeneric("Error al Intentar Registrar la Mejora","500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<ImprovementsDto> getByIdimprovements(UUID improvementsId) {
        return Optional.ofNullable(improvementsRepository.findById(improvementsId).map(improvements -> modelMapper.map(improvements,ImprovementsDto.class))
                .orElseThrow(()-> new MessageGeneric("No se encuetra disponible la Mejora","404",HttpStatus.NOT_FOUND)));
    }

    @Override
    public Boolean deleteImprovements(UUID idImprovements) {
        if (improvementsRepository.findById(idImprovements).isPresent()){
            improvementsRepository.deleteById(idImprovements);
            return true;
        }
        return false;
    }
}
