package com.wposs.scrum_back.userstory.entity;

import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.improvements.entity.Improvements;
import com.wposs.scrum_back.subProject.entity.SubProject;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.userstorystatus.entity.UserStoryStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_story", schema = "wposs")
public class UserStory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_story_id")
    private UUID userStoryId;

    @Column(name = "user_story_code",nullable = false)
    private String userStoryCode;
    @Column(name = "user_story_name", length = 30)
    private String userStoryName;

    @Column(name = "user_story_score")
    private Integer userStoryScore;

    @Lob
    @Column(name = "user_story_archive")
    private String userStoryArchive;

    @Column(name = "user_story_status_id")
    private Long userStoryStateId;

    @Column(name = "fecha_maxima",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaMaxima;

    @Column(name = "sub_project_id", nullable = false)
    private UUID subProjectId;

    @ManyToOne
    @JoinColumn(name = "user_story_status_id", insertable = false, updatable = false)
    private UserStoryStatus userStoryState;

    @ManyToOne
    @JoinColumn(name = "sub_project_id", insertable = false, updatable = false)
    private SubProject subProject;

    @OneToMany(mappedBy = "userStory",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Board> boards;

    @OneToMany(mappedBy = "userStory",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<TaskTeam> taskTeams;

    @OneToMany(mappedBy = "userStory",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Improvements> improvements;

    public List<TaskTeam> getTaskTeams() {
        return taskTeams;
    }

    public void setTaskTeams(List<TaskTeam> taskTeams) {
        this.taskTeams = taskTeams;
    }

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

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }

    public String getUserStoryName() {
        return userStoryName;
    }

    public void setUserStoryName(String userStoryName) {
        this.userStoryName = userStoryName;
    }

    public Integer getUserStoryScore() {
        return userStoryScore;
    }

    public void setUserStoryScore(Integer userStoryScore) {
        this.userStoryScore = userStoryScore;
    }

    public String getUserStoryArchive() {
        return userStoryArchive;
    }

    public void setUserStoryArchive(String userStoryArchive) {
        this.userStoryArchive = userStoryArchive;
    }

    public Long getUserStoryStateId() {
        return userStoryStateId;
    }

    public void setUserStoryStateId(Long userStoryStateId) {
        this.userStoryStateId = userStoryStateId;
    }

    public UUID getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(UUID subProjectId) {
        this.subProjectId = subProjectId;
    }

    public UserStoryStatus getUserStoryState() {
        return userStoryState;
    }

    public void setUserStoryState(UserStoryStatus userStoryState) {
        this.userStoryState = userStoryState;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getUserStoryCode() {
        return userStoryCode;
    }

    public void setUserStoryCode(String userStoryCode) {
        this.userStoryCode = userStoryCode;
    }
}
