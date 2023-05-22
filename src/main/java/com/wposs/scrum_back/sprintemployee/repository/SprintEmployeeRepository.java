package com.wposs.scrum_back.sprintemployee.repository;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SprintEmployeeRepository extends JpaRepository<SprintEmployee,Long> {
    Boolean existsById(SprintEmployeePk id);
}
