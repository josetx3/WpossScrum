package com.wposs.scrum_back.subProject.repository;

import com.wposs.scrum_back.subProject.dto.SubProjectDto;
import com.wposs.scrum_back.subProject.entity.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubProjectRepository  extends JpaRepository<SubProject, UUID> {

   /* @Query(value = "SELECT * FROM wposs.sub_project WHERE sub_project_name=?1",nativeQuery = true)
    SubProjectDto projectExis(String subProjectName);*/

    Boolean existsBySubProjectName(String subProjectName);

    List<SubProject> getByProjectId(UUID projectId);
}
