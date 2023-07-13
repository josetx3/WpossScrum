package com.wposs.scrum_back.employe.service;

import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import com.wposs.scrum_back.employe.dto.EmployeDto;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.employe.entity.response;
import com.wposs.scrum_back.employe.repository.EmployeeRepository;
import com.wposs.scrum_back.utils.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
        if (employeeRepository.existsByEmployeeEmail(employee.getEmployeeEmail())){
            throw new MessageGeneric("El empleado con este gmail: "+employee.getEmployeeEmail()+" Ya se encuentra Registrado","409",HttpStatus.CONFLICT);
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
    public EmployeDto updateEmployePass(UUID idEmploye,String password, EmployeDto employeDto) {
        try {
            Optional<Employee> employee1 = employeeRepository.findById(idEmploye);
            if (passwordEncoder.matches(password, employee1.get().getEmployeePassword())) {
                return employeeRepository.findById(idEmploye).map(employee -> {
                    employee.setEmployeeName((employeDto.getEmployeeName() != null) ? employeDto.getEmployeeName() : employee.getEmployeeName());
                    employee.setEmployeeCharge((employeDto.getEmployeeCharge() != null) ? employeDto.getEmployeeCharge() : employee.getEmployeeCharge());
                    employee.setEmployeeEmail((employeDto.getEmployeeEmail() != null) ? employeDto.getEmployeeEmail() : employee.getEmployeeEmail());
                    employeDto.setEmployeePassword(passwordEncoder.encode(employeDto.getEmployeePassword()));
                    employee.setEmployeePassword((employeDto.getEmployeePassword() != null) ? employeDto.getEmployeePassword() : employee.getEmployeePassword());
                    employee.setEmployeeKnowledge((employeDto.getEmployeeKnowledge() != null) ? employeDto.getEmployeeKnowledge() : employee.getEmployeeKnowledge());
                    return modelMapper.map(employeeRepository.save(employee), EmployeDto.class);
                }).orElseThrow(() -> new MessageGeneric("No se encontro el Empleado a Actualizar", "404", HttpStatus.NOT_FOUND));
            }
            throw new MessageGeneric("CONTRASEÑA NO VALIDA","",HttpStatus.NOT_FOUND);
            //employeDto.setEmployeePassword(password);
            //return employeDto;
        }catch (Exception e){
            throw new MessageGeneric("CONTRASEÑA NO VALIDA","",HttpStatus.NOT_FOUND);
            //employeDto.setEmployeePassword(password);
            //return employeDto;
        }
    }

    @Override
    public Boolean deleteEmploye(UUID idEmployee) {
        if(employeeRepository.findById(idEmployee).isPresent()){
            employeeRepository.deleteById(idEmployee);
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeDto> getEmployeToTeam(UUID idTeam) {
        return employeeRepository.getEmployeToTeam(idTeam).stream().map(employee -> {
            return modelMapper.map(employee,EmployeDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<EmployeDto> getEmployeToTeam2(UUID idTeam) {
        return employeeRepository.getEmployeToTeam(idTeam).stream().map(employee -> {
            return modelMapper.map(employee,EmployeDto.class);
        }).collect(Collectors.toList());
    }


    @Override
    public List<EmployeDto> getAllEmployeeNoExitsAndTeam(UUID idTeam) {
        return employeeRepository.getAllnoExistAndTeam(idTeam).stream().map(employeDto -> {
            return modelMapper.map(employeDto,EmployeDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity login(String employeEmail, String employePassword) {
        try{
            Employee employee = employeeRepository.findByEmployeeEmail(employeEmail);
            if(passwordEncoder.matches(employePassword,employee.getEmployeePassword())){
                String token = jwtUtil.create(String.valueOf(employee.getEmployeeId()), employee.getEmployeeEmail());
                response respon= new response();
                respon.setToken(token);
                respon.setNameE(employee.getEmployeeName());
                respon.setCharge(employee.getEmployeeCharge());
                respon.setIdE(String.valueOf(employee.getEmployeeId()));
                return ResponseEntity.ok(respon);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

}
