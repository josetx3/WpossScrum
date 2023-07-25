package com.wposs.scrum_back.subProject.repository;

import com.wposs.scrum_back.subProject.entity.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubProjectRepository  extends JpaRepository<SubProject, UUID> {

   @Query(value = "select sub.* from wposs.sub_project sub inner join wposs.project pr on sub.project_id=pr.project_id\n" +
           "inner join wposs.client cl on pr.client_id=cl.client_id where  cl.client_id=?1",nativeQuery = true)
    List<SubProject> findSubprojectsByClient(String clientId);

    Boolean existsBySubProjectName(String subProjectName);

    SubProject getBySubProjectName( String subProjectName);

    List<SubProject> getByProjectId(UUID projectId);
}
