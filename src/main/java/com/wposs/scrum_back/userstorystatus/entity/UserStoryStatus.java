package com.wposs.scrum_back.userstorystatus.entity;


import com.wposs.scrum_back.userstory.entity.UserStory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_story_status", schema = "wposs")
public class UserStoryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_story_status_id")
    private Long userStoryStateId;

    @Column(name = "user_story_status_name")
    private String userStoryStateName;

    @OneToMany(mappedBy = "userStoryState")
    private List<UserStory> userStories;

    public Long getUserStoryStateId() {
        return userStoryStateId;
    }

    public void setUserStoryStateId(Long userStoryStateId) {
        this.userStoryStateId = userStoryStateId;
    }

    public String getUserStoryStateName() {
        return userStoryStateName;
    }

    public void setUserStoryStateName(String userStoryStateName) {
        this.userStoryStateName = userStoryStateName;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
