package com.wposs.scrum_back.client.repository;

import com.wposs.scrum_back.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Boolean existsByClientName(String clientName);

}
