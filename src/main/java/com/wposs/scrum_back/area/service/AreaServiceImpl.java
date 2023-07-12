package com.wposs.scrum_back.area.service;

import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.area.dto.AreaDto;
import com.wposs.scrum_back.area.entity.Area;
import com.wposs.scrum_back.area.repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AreaDto> getAllArea() {
        return areaRepository.findAll().stream().map(area -> {
            return modelMapper.map(area,AreaDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<AreaDto> getAllAreaByIdEmployee(UUID idEmployee) {
        return areaRepository.findByEmployee(idEmployee).stream().map(area -> {
            return modelMapper.map(area,AreaDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<AreaDto> getAreaId(UUID idArea) {
        return Optional.ofNullable(areaRepository.getAreaId(idArea).map(area -> {
            return modelMapper.map(area, AreaDto.class);
        }).orElseThrow(() -> new MessageGeneric("El Area solicitada no se encuentra registrada", "404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public AreaDto saveArea(AreaDto areaDto) {
        if(areaRepository.existsByAreaName(areaDto.getAreaName())){
            throw new MessageGeneric("Ya existe un Area Con este nombre: "+areaDto.getAreaName(),"409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(areaRepository.save(modelMapper.map(areaDto,Area.class)),AreaDto.class);
        }catch (Exception ex){
            System.out.println("hola");
            throw new InternalServerException("Error inesperado al intentar registrar el Area,JSON mal estructurado","500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AreaDto updateArea(UUID idArea, AreaDto areaDto) {
        if(!areaRepository.existsByAreaName(areaDto.getAreaName())){
            return areaRepository.findById(idArea).map(area -> {
                area.setAreaName((areaDto.getAreaName()!=null)?areaDto.getAreaName():area.getAreaName());
                return modelMapper.map(areaRepository.save(area),AreaDto.class);
            }).orElseThrow(()->new MessageGeneric("No se encontro la Categopria a actualizar","404",HttpStatus.NOT_FOUND));
        }
        throw new MessageGeneric("Ya existe un Area Con este nombre: "+areaDto.getAreaName(),"409",HttpStatus.CONFLICT);
    }

    @Override
    public Boolean deleteArea(UUID idArea) {
        if(areaRepository.findById(idArea).isPresent()){
            areaRepository.deleteById(idArea);
            return true;
        }
        return false;
    }
}
