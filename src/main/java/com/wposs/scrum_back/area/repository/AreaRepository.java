package com.wposs.scrum_back.area.repository;

import com.wposs.scrum_back.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface   AreaRepository extends JpaRepository<Area, UUID> {

    Boolean existsByAreaName(String areaName);

    @Query(value = "SELECT * FROM wposs.area WHERE area_id=?1",nativeQuery = true)
    Optional<Area> getAreaId(UUID idArea);
}
