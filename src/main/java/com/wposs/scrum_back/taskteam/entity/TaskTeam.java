package com.wposs.scrum_back.taskteam.entity;

import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.improvements.entity.Improvements;
import com.wposs.scrum_back.team.entity.Team;
import com.wposs.scrum_back.userstory.entity.UserStory;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_team", schema = "wposs")
public class TaskTeam {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "task_team_id")
    private UUID taskTeamId;
    @Column(name = "task_team_name")
    private String taskName;
    @Column(name = "task_team_hours")
    private Integer taskHours;
    @Column(name = "task_team_state")
    private String taskState;
    @Column(name = "fk_team")
    private UUID teamId;

    @Column(name = "fk_user_story")
    private  UUID userStoryId;
    @ManyToOne
    @JoinColumn(name = "fk_team",insertable = false,updatable = false)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "fk_user_story",insertable = false,updatable = false)
    private UserStory userStory;
    @OneToMany(mappedBy = "taskTeam",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Board> boards;

    @OneToMany(mappedBy = "taskTeam",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Improvements>  improvements;

    public List<Improvements> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<Improvements> improvements) {
        this.improvements = improvements;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public UUID getTaskTeamId() {
        return taskTeamId;
    }

    public void setTaskTeamId(UUID taskTeamId) {
        this.taskTeamId = taskTeamId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(Integer taskHours) {
        this.taskHours = taskHours;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }
}
