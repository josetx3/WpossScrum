package com.wposs.scrum_back.sprintuserstory.entity;

import com.wposs.scrum_back.sprintemployee.entity.SprintEmployeePk;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class SprintUserstoryPk implements Serializable {
    public SprintUserstoryPk(UUID userStoryId, UUID sprintId) {
        this.userStoryId = userStoryId;
        this.sprintId = sprintId;

    }

    @Column(name = "fk_user_story_id",nullable = false)
    @Type(type = "pg-uuid")
    private UUID userStoryId;
    @Column(name = "fk_sprint_id", nullable = false)
    @Type(type = "pg-uuid")
    private UUID sprintId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintUserstoryPk)) return false;
        SprintUserstoryPk that = (SprintUserstoryPk) o;
        return Objects.equals(getSprintId(), that.getSprintId()) &&
                Objects.equals(getUserStoryId(), that.getUserStoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSprintId(), getUserStoryId());
    }

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
    }

    public SprintUserstoryPk() {}


}
