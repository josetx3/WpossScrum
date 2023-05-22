package com.wposs.scrum_back.observation.entity;

import com.wposs.scrum_back.improvements.entity.Improvements;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "observation",schema = "wposs")
public class Observation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "observation_id")
    private UUID observationId;
    @Column(name = "observationName",nullable = false,unique = true,length = 30)
    private String observationName;

    @OneToMany(mappedBy = "observation",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Improvements> improvements;

    public List<Improvements> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<Improvements> improvements) {
        this.improvements = improvements;
    }

    public UUID getObservationId() {
        return observationId;
    }

    public void setObservationId(UUID observationId) {
        this.observationId = observationId;
    }

    public String getObservationName() {
        return observationName;
    }

    public void setObservationName(String observationName) {
        this.observationName = observationName;
    }
}
