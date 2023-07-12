package com.wposs.scrum_back.area.repository;

import com.wposs.scrum_back.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface   AreaRepository extends JpaRepository<Area, UUID> {

    Boolean existsByAreaName(String areaName);

    @Query(value = "select distinct ar.* from wposs.employee em inner join wposs.team_employee te on em.employee_id = te.employee_id\n" +
            "inner join wposs.team tea on te.team_id = tea.team_id \n" +
            "inner join wposs.area ar on tea.area_id = ar.area_id\n" +
            "where em.employee_id=?1",nativeQuery = true)
    List<Area> findByEmployee(UUID idEmployee);
    @Query(value = "SELECT * FROM wposs.area WHERE area_id=?1",nativeQuery = true)
    Optional<Area> getAreaId(UUID idArea);

    Area getByAreaName(String areaName);
}
