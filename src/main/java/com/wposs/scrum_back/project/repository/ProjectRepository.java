package com.wposs.scrum_back.project.repository;

import com.wposs.scrum_back.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends CrudRepository<Project, UUID>, JpaRepository<Project, UUID> {

    Boolean existsByProjectNameAndAreaId(String projectName,UUID idArea);
    List<Project> getByAreaId(UUID areaId);

}
