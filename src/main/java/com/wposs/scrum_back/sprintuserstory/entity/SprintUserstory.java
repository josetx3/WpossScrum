package com.wposs.scrum_back.sprintuserstory.entity;



import com.wposs.scrum_back.sprint.entity.Sprint;
import com.wposs.scrum_back.userstory.entity.UserStory;

import javax.persistence.*;

@Entity
@Table (name= "sprint_userstory", schema = "wposs")
public class SprintUserstory {

    @EmbeddedId
    private SprintUserstoryPk id;

    @Column(name = "points",nullable = false)
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "fk_Sprint_id",insertable = false,updatable = false)
    private Sprint sprint;

    @ManyToOne
    @JoinColumn(name = "fk_user_story_id",insertable = false,updatable = false)
    private UserStory userStory;

    public SprintUserstoryPk getId() {
        return id;
    }

    public void setId(SprintUserstoryPk id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

}
