package com.wposs.scrum_back.sprintemployee.repository;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//List<TablaIntermedia> findByEntidadAAndEntidadB(EntidadA entidadA, EntidadB entidadB);

@Repository
public interface SprintEmployeeRepository extends JpaRepository<SprintEmployee, Long> {
    Boolean existsById( SprintEmployeePk id);
    Boolean existsSprintEmployeeById(long idEmployee);

    //@Query(value = "SELECT * FROM wposs.employee em INNER JOIN wposs.team_employee te ON em.employee_id = te.employee_id WHERE te.team_id=?1",nativeQuery = true)
    @Query(value = "SELECT em.employee_name, se.days_leave, se.observations,  se.percentage, se.percentage_final from wposs.team te INNER JOIN wposs.team_employee tem ON te.team_id = tem.team_id INNER JOIN wposs.employee em ON tem.employee_id = em.employee_id INNER JOIN wposs.sprints_employee se ON em.employee_id = se.fk_employee_id WHERE te.team_id =?1",nativeQuery = true)
    List<Object[]> getEmployeToTeam(UUID idTeam);
    // Optional<SprintEmployeeDto> getBySprintEmployeeId(long idEmployee);

}
