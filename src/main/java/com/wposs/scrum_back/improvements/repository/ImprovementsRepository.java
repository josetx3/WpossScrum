package com.wposs.scrum_back.improvements.repository;

import com.wposs.scrum_back.improvements.entity.Improvements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ImprovementsRepository extends JpaRepository<Improvements, UUID> {
}
