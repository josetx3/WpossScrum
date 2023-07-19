package com.wposs.scrum_back.taskteam.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.board.repository.BoardRepository;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDto;
import com.wposs.scrum_back.taskteam.dto.TaskTeamDtoRequest;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.taskteam.repository.TaskTeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskTeamServiceImpl implements TaskTeamService {
    @Autowired
    private TaskTeamRepository taskTeamRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
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
    @Transactional
    public List<TaskTeamDto> getTaskTeamToIdTeamAndIdUserStory(UUID teamId, UUID userStoryId) {
        return taskTeamRepository.getTaskTeamByTeamAndUserStory(teamId,userStoryId).stream().map(taskTeam -> {
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
        taskTeam.setTaskState("PENDIENTE");
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
        return taskTeamRepository.findById(idTasTeam).map(taskTeam -> {
            taskTeam.setTaskName((taskTeamDto.getTaskName()!=null)? taskTeamDto.getTaskName() : taskTeam.getTaskName());
            taskTeam.setTeamId((taskTeamDto.getTeamId()!=null)?taskTeamDto.getTeamId():taskTeam.getTeamId());
            taskTeam.setTaskHours((taskTeamDto.getTaskHours()!=null)? taskTeamDto.getTaskHours() : taskTeam.getTaskHours());
            return modelMapper.map(taskTeamRepository.save(taskTeam),TaskTeamDto.class);
        }).orElseThrow(()->new MessageGeneric("Error,No se encontro la Tarea a Actualizar","404",HttpStatus.NOT_FOUND));
    }
    @Override
    public TaskTeamDto updateTaskTeamState(UUID idTasTeam,UUID idBoard, TaskTeamDto taskTeamDto) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        ZoneId zonaHorariaColombia = ZoneId.of("America/Bogota");
        fechaHoraActual = fechaHoraActual.atZone(ZoneId.systemDefault()).withZoneSameInstant(zonaHorariaColombia).toLocalDateTime();
        LocalDateTime finalFechaHoraActual = fechaHoraActual;

        Optional<Board> board = boardRepository.findById(idBoard);
        board.get().setFinishDate(finalFechaHoraActual);
        boardRepository.save(board.get());
        return taskTeamRepository.findById(idTasTeam).map(taskTeam -> {
            taskTeam.setTaskState("FINALIZADA");
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
