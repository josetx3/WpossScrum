package com.wposs.scrum_back.userstory.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.userstory.dto.UserStoryDto;
import com.wposs.scrum_back.userstory.entity.UserStory;
import com.wposs.scrum_back.userstory.repository.UserStoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService{
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserStoryDto> getAllUserStory() {
        return userStoryRepository.findAll().stream().map(userStory -> {
            return modelMapper.map(userStory,UserStoryDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserStoryDto> getAllUserStoryToSubProject(UUID idSubProject) {
        return userStoryRepository.findBySubProjectId(idSubProject).stream()
                .map(userStory -> {
                    return modelMapper.map(userStory,UserStoryDto.class);
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<UserStoryDto> getUserStoryById(UUID idUserStory) {
        return Optional.ofNullable(userStoryRepository.findById(idUserStory)
                .map(userStory ->modelMapper.map(userStory,UserStoryDto.class))
                .orElseThrow(()->new MessageGeneric("No se encunetra la Historia de Usuario que esta Solicitando","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public UserStoryDto saveUserStory(UserStoryDto userStoryDto) {
        UserStory userStory = modelMapper.map(userStoryDto,UserStory.class);
        if(userStoryRepository.existsByUserStoryNameAndSubProjectId(userStory.getUserStoryName(),userStory.getSubProjectId())){
            throw new MessageGeneric("Error al intentar Registrar: "+userStory.getUserStoryName()+" Ya se encuentra Registrada al mismo SubProjecto","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(userStoryRepository.save(userStory),UserStoryDto.class);
        }catch (Exception ex){
            throw new RequestException("Surjio un Error al intertar Registrar la Historia de Usuario,JSON mal estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserStoryDto updateUserStory(UUID idUserStory, UserStoryDto userStoryDto) {
        return userStoryRepository.findById(idUserStory).map(userStory -> {
            userStory.setUserStoryName((userStoryDto.getUserStoryName() != null)? userStoryDto.getUserStoryName(): userStory.getUserStoryName());
            userStory.setUserStoryArchive((userStoryDto.getUserStoryArchive() != null)? userStoryDto.getUserStoryArchive() : userStory.getUserStoryArchive());
            userStory.setUserStoryScore((userStoryDto.getUserStoryScore() != null) ? userStoryDto.getUserStoryScore() : userStory.getUserStoryScore());
            userStory.setUserStoryStateId((userStoryDto.getUserStoryStateId() != null) ? userStoryDto.getUserStoryStateId(): userStory.getUserStoryStateId());
            userStory.setSubProjectId((userStoryDto.getSubProjectId() != null)? userStoryDto.getSubProjectId() : userStory.getSubProjectId());
            return modelMapper.map(userStoryRepository.save(userStory),UserStoryDto.class);
        }).orElseThrow(()-> new MessageGeneric("No se encunetra la Historia de Usuario a Actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public List<UserStoryDto> getAllUserStoryToTeam(UUID idTeam) {
        return userStoryRepository.getAllSserStoryToTeam(idTeam)
                .stream()
                .map(userStory -> {
                    return modelMapper.map(userStory,UserStoryDto.class);
                }).collect(Collectors.toList());
    }


}
