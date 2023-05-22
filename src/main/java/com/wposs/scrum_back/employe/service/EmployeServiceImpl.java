package com.wposs.scrum_back.employe.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.employe.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeDto> getAllEmploye() {
        return employeeRepository.findAll().stream().map(employee -> {
            return modelMapper.map(employee,EmployeDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeDto> getEmployeId(UUID idEmploye) {
        return Optional.ofNullable(employeeRepository.findById(idEmploye).map(employee -> {
            return modelMapper.map(employee,EmployeDto.class);
        }).orElseThrow(()->new MessageGeneric("No se encontro el empleado que esta solicitando","404", HttpStatus.NOT_FOUND)));
    }

    @Override
    public EmployeDto seveEmploye(EmployeDto employeDto) {
        Employee employee = modelMapper.map(employeDto,Employee.class);
        if (employeeRepository.existsByEmployeeName(employee.getEmployeeName())){
            throw new MessageGeneric("El empleado con este Nombre: "+employee.getEmployeeName()+" Ya se encuentra Registrado","409",HttpStatus.CONFLICT);
        }
        try {
            return modelMapper.map(employeeRepository.save(employee),EmployeDto.class);
        }catch (Exception ex){
            throw new RequestException("Surguio un error al intertar Registrar El Empleado,JSON mal estructurado","400",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public EmployeDto updateEmploye(UUID idEmploye, EmployeDto employeDto) {
        return employeeRepository.findById(idEmploye).map(employee -> {
            employee.setEmployeeName((employeDto.getEmployeeName()!=null)?employeDto.getEmployeeName():employee.getEmployeeName());
            employee.setEmployeeCharge((employeDto.getEmployeeCharge()!=null)? employeDto.getEmployeeCharge() : employee.getEmployeeCharge());
            employee.setEmployeeEmail((employeDto.getEmployeeEmail()!=null)? employeDto.getEmployeeEmail() : employee.getEmployeeEmail());
            employee.setEmployeeKnowledge((employeDto.getEmployeeKnowledge()!=null)? employeDto.getEmployeeKnowledge() : employee.getEmployeeKnowledge());
            return modelMapper.map(employeeRepository.save(employee),EmployeDto.class);
        }).orElseThrow(()-> new MessageGeneric("No se encontro el Empleado a Actualizar","404",HttpStatus.NOT_FOUND));
    }

    @Override
    public List<EmployeDto> getEmployeToTeam(UUID idTeam) {
        return employeeRepository.getEmployeToTeam(idTeam).stream().map(employee -> {
            return modelMapper.map(employee,EmployeDto.class);
        }).collect(Collectors.toList());
    }

}
