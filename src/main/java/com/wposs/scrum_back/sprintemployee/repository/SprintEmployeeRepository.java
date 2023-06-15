package com.wposs.scrum_back.sprintemployee.repository;

import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//List<TablaIntermedia> findByEntidadAAndEntidadB(EntidadA entidadA, EntidadB entidadB);

@Repository
public interface SprintEmployeeRepository extends JpaRepository<SprintEmployee, Long> {
    Boolean existsById( SprintEmployeePk id);
    Boolean existsSprintEmployeeById(long idEmployee);
   // Optional<SprintEmployeeDto> getBySprintEmployeeId(long idEmployee);

}
