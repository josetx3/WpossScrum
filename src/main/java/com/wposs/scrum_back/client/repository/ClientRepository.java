package com.wposs.scrum_back.client.repository;

import com.wposs.scrum_back.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Boolean existsByClientName(String clientName);

    @Transactional
    @Modifying
    @Query(value = "update wposs.client set client_id=?1, client_name=?2 where client_id=?3 ",
            nativeQuery = true)
    int updateclient(String clientNitNew,String clientName,String clientNit);

}
