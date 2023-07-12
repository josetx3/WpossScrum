package com.wposs.scrum_back.userstorystatus.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import com.wposs.scrum_back.userstorystatus.entity.UserStoryStatus;
import com.wposs.scrum_back.userstorystatus.repository.UserStoryStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStoryStatusServiceImpl implements UserStoryStatusService {
    @Autowired
    private UserStoryStatusRepository userStoryStatusRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserStoryStatusDto> gatAll() {
        return userStoryStatusRepository.getAllStatus().stream().map(userStoryStatusRepository1 -> {
            return modelMapper.map(userStoryStatusRepository1,UserStoryStatusDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public UserStoryStatusDto saveStatus(UserStoryStatusDto userStoryStatusDto) {
        UserStoryStatus userStoryStatus = modelMapper.map(userStoryStatusDto,UserStoryStatus.class);
        if(userStoryStatusRepository.existsByUserStoryStateName(userStoryStatus.getUserStoryStateName())){
            throw new MessageGeneric("Ya existe un estado con este Nombre: "+userStoryStatus.getUserStoryStateName()+" Registrado","", HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(userStoryStatusRepository.save(userStoryStatus),UserStoryStatusDto.class);
        }catch (Exception ex){
            throw new RequestException("Error al intentar Registrar el estado,Json mal estrcuturado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserStoryStatusDto updateUserStoryStatus(Long idStatus, UserStoryStatusDto userStoryStatusDto) {
        return userStoryStatusRepository.findById(idStatus).map(userStoryStatus -> {
            userStoryStatus.setUserStoryStateName((userStoryStatusDto.getUserStoryStateName()!=null)?userStoryStatusDto.getUserStoryStateName():userStoryStatus.getUserStoryStateName());
            return modelMapper.map(userStoryStatusRepository.save(userStoryStatus),UserStoryStatusDto.class);
        }).orElseThrow(()->new MessageGeneric("No esta disponible el estado a actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public Boolean deleteProducto(Long idStatus) {
        if (userStoryStatusRepository.findById(idStatus).isPresent()){
            userStoryStatusRepository.deleteById(idStatus);
            return true;
        }
        return false;
    }
}
