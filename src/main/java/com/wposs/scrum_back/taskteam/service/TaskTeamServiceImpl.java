package com.wposs.scrum_back.taskteam.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDtoRequest;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.taskteam.repository.TaskTeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskTeamServiceImpl implements TaskTeamService{
    @Autowired
    private TaskTeamRepository taskTeamRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<TaskTeamDto> getAllTaskTeam() {
        return taskTeamRepository.getAllTaskTeam().stream().map(taskTeam -> {
            return modelMapper.map(taskTeam,TaskTeamDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TaskTeamDtoRequest> getTaskTeamToIdTeamAndUserStory(UUID teamId, UUID userStoryId) {
        List<Object[]> TaskTeam=taskTeamRepository.getDataTaskByTeamAndUserStory(teamId,userStoryId);
        List<TaskTeamDtoRequest> taskTeamDtoRequests = new ArrayList<>();
        if(TaskTeam.isEmpty()){
            throw new MessageGeneric("Error","404",HttpStatus.NOT_FOUND);
        }
        for (Object[] taskTeam:TaskTeam) {
            TaskTeam taskTeam1= taskTeamRepository.getByTaskNameAndTeamId(taskTeam[0].toString(),teamId);

            TaskTeamDtoRequest taskTeamDtoRequest = new TaskTeamDtoRequest(
                    taskTeam1.getTaskTeamId(),
                    taskTeam[0].toString(),
                    Integer.parseInt(taskTeam[1].toString()),
                    Integer.parseInt(taskTeam[2].toString())
            );
            taskTeamDtoRequests.add(taskTeamDtoRequest);
        }
        return taskTeamDtoRequests;
    }

    @Override
    public List<TaskTeamDto> getTaskTeamToIdTeam(UUID idTeam) {
        return taskTeamRepository.getByTeamId(idTeam).stream().map(taskTeam -> {
            return modelMapper.map(taskTeam,TaskTeamDto.class);
        }).collect(Collectors.toList());
    }
    @Override
    public Optional<TaskTeamDto> getTaskTeamById(UUID idTaskTeam) {
        return Optional.ofNullable(taskTeamRepository.findById(idTaskTeam)
                .map(taskTeam ->modelMapper.map(taskTeam,TaskTeamDto.class))
                .orElseThrow(()->new MessageGeneric("No esta disponible la Tarea del Equipo que esta solicitando","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public TaskTeamDto saveTaskTeam(TaskTeamDto taskTeamDto) {
        TaskTeam taskTeam = modelMapper.map(taskTeamDto,TaskTeam.class);
        if (taskTeamRepository.existsByTaskNameAndTeamId(taskTeam.getTaskName(),taskTeam.getTeamId())){
            throw new MessageGeneric("Ya se encuentra la tarea: "+taskTeam.getTaskName()+" asociada al mismo equipo ","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(taskTeamRepository.save(taskTeam),TaskTeamDto.class);
        }catch (Exception ex){
            throw new RequestException("Surjio un error al intertar Registrara la Tarea,Json mal estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public TaskTeamDto updateTaskTeam(UUID idTasTeam, TaskTeamDto taskTeamDto) {
        TaskTeam taskTeamP = modelMapper.map(taskTeamDto,TaskTeam.class);
        if (taskTeamRepository.existsByTaskNameAndTeamId(taskTeamP.getTaskName(),taskTeamP.getTeamId())){
            throw new MessageGeneric("Ya se encuentra la tarea: "+taskTeamP.getTaskName()+" asociada al mismo equipo ","409",HttpStatus.CONFLICT);
        }
        return taskTeamRepository.findById(idTasTeam).map(taskTeam -> {
            taskTeam.setTaskName((taskTeamDto.getTaskName()!=null)? taskTeamDto.getTaskName() : taskTeam.getTaskName());
            taskTeam.setTeamId((taskTeamDto.getTeamId()!=null)?taskTeamDto.getTeamId():taskTeam.getTeamId());
            return modelMapper.map(taskTeamRepository.save(taskTeam),TaskTeamDto.class);
        }).orElseThrow(()->new MessageGeneric("Error,No se encontro la Tarea a Actualizar","404",HttpStatus.NOT_FOUND));
    }
    @Override
    public TaskTeamDto updateTaskTeamHours(UUID idTasTeam, TaskTeamDto taskTeamDto) {
        return taskTeamRepository.findById(idTasTeam).map(taskTeam -> {
            taskTeam.setTaskHours((taskTeamDto.getTaskHours()!=null)? taskTeamDto.getTaskHours() : taskTeam.getTaskHours());

            return modelMapper.map(taskTeamRepository.save(taskTeam),TaskTeamDto.class);
        }).orElseThrow(()->new MessageGeneric("Error,No se encontro la Tarea a Actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public Boolean deleteTaskTeam(UUID idTasTeam) {
        if(this.getTaskTeamById(idTasTeam).isPresent()){
            taskTeamRepository.deleteById(idTasTeam);
            return true;
        }
        return false;
    }
}
