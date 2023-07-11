package com.wposs.scrum_back.scoresprintdays.entity;

import com.wposs.scrum_back.sprint.entity.Sprint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Score_sprint_days",schema = "wposs")
public class ScoringSprintDays {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID scoreSprintId;
    @Column(name = "day_sprint",nullable = false)
    @CreationTimestamp
    private Date date;

    @Column(name = "day_sprint_update" )
    @UpdateTimestamp
    private Date date_update;
    @Column(name = "score_sprint",nullable = false)
    private Double scoreSprint;
    @Column(name = "fk_sprint_id",nullable = false)
    private UUID sprintId;

    @ManyToOne
    @JoinColumn(name = "fk_sprint_id",insertable = false,updatable = false)
    private Sprint sprint;

    public UUID getScoreSprintId() {
        return scoreSprintId;
    }

    public void setScoreSprintId(UUID scoreSprintId) {
        this.scoreSprintId = scoreSprintId;
    }

   public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getScoreSprint() {
        return scoreSprint;
    }

    public void setScoreSprint(Double scoreSprint) {
        this.scoreSprint = scoreSprint;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
