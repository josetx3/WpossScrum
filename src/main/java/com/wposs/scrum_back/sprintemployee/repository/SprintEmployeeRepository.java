package com.wposs.scrum_back.sprintemployee.repository;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDto;
import com.wposs.scrum_back.sprintemployee.dto.SprintEmployeeDtoRequest;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//List<TablaIntermedia> findByEntidadAAndEntidadB(EntidadA entidadA, EntidadB entidadB);

@Repository
public interface SprintEmployeeRepository extends JpaRepository<SprintEmployee, Long> {
    Boolean existsById( SprintEmployeePk id);
    Boolean existsSprintEmployeeById(SprintEmployeePk primaryKey);



    //@Query(value = "SELECT em.employee_name, se.days_leave, se.observations,  se.percentage, se.percentage_final from wposs.team te INNER JOIN wposs.team_employee tem ON te.team_id = tem.team_id INNER JOIN wposs.employee em ON tem.employee_id = em.employee_id INNER JOIN wposs.sprints_employee se ON em.employee_id = se.fk_employee_id WHERE te.team_id =?1",nativeQuery = true)
    @Query(value = "SELECT em.employee_name,em.employee_email, se.days_leave, se.observations, se.percentage, se.percentage_final from wposs.team te inner join wposs.team_employee tem on te.team_id = tem.team_id inner join wposs.employee em ON em.employee_id = tem.employee_id inner join wposs.sprints_employee se ON se.fk_employee_id = em.employee_id where se.fk_sprint_id = ?1 and tem.team_id = ?2",nativeQuery = true)
    List<Object[]> getEmployeToTeam(UUID idSprint, UUID idTeam);


    @Query("SELECT se FROM SprintEmployee se WHERE  se.id= :primaryKey")
    Optional<SprintEmployee> findByPrimaryKey(@Param("primaryKey") SprintEmployeePk primaryKey);

    @Query("SELECT se FROM SprintEmployee se WHERE  se.id= :primaryKey")
   SprintEmployee findByPrimaryKey1(@Param("primaryKey") SprintEmployeePk primaryKey);

    //@Query(value = "select * from wposs.sprints_employee where fk_employee_id = ?1 and fk_sprint_id = ?2",nativeQuery = true)
    //  Optional<SprintEmployeeDtoRequest> getEmploye(UUID idEmployee, UUID idSprint);

    //Optional<SprintEmployeeDtoRequest> getBySprintEmployeeId(Long idEmployee);

}
