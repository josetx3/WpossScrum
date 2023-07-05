package com.wposs.scrum_back.sprintuserstory.repository;

import com.wposs.scrum_back.sprintuserstory.entity.SprintUserstory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintUserstoryRepository extends JpaRepository<SprintUserstory, Long> {


}
