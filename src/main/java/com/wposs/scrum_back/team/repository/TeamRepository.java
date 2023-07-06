package com.wposs.scrum_back.team.repository;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    Boolean existsByTeamName(String teamName);

    Team getByTeamName(String teamName);
    List<Team> getByAreaId(UUID areaId);
}
