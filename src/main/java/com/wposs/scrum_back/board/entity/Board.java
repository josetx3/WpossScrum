package com.wposs.scrum_back.board.entity;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import com.wposs.scrum_back.team.entity.Team;
import com.wposs.scrum_back.userstory.entity.UserStory;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "board",schema = "wposs")
public class Board {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_board")
    private UUID idBoard;
    @Column(name = "fk_team",nullable = false,length = 40)
    private UUID teamId;
    @Column(name = "fk_user_story",nullable = false,length = 40)
    private UUID userStoryId;
    @Column(name = "fk_task_team",nullable = false,length = 40)
    private UUID taskTeamId;
    @Column(name = "fk_employee",length = 40)
    private UUID employeeId;
    @Column(name = "fecha_board")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assingDate;

    @Column(name = "fecha_board_terminado")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishDate;

    @ManyToOne
    @JoinColumn(name = "fk_team",insertable = false,updatable = false)
    private Team teamBoard;
    @ManyToOne
    @JoinColumn(name = "fk_user_story",insertable = false,updatable = false)
    private UserStory userStory;

    @ManyToOne
    @JoinColumn(name = "fk_task_team",insertable = false,updatable = false)
    private TaskTeam taskTeam;

    @ManyToOne
    @JoinColumn(name = "fk_employee",insertable = false,updatable = false)
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TaskTeam getTaskTeam() {
        return taskTeam;
    }

    public void setTaskTeam(TaskTeam taskTeam) {
        this.taskTeam = taskTeam;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public UUID getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(UUID idBoard) {
        this.idBoard = idBoard;
    }

    public LocalDateTime getAssingDate() {
        return assingDate;
    }

    public void setAssingDate(LocalDateTime assingDate) {
        this.assingDate = assingDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public Team getTeam() {
        return teamBoard;
    }

    public void setTeam(Team team) {
        this.teamBoard = team;
    }

    public Team getTeamBoard() {
        return teamBoard;
    }

    public void setTeamBoard(Team teamBoard) {
        this.teamBoard = teamBoard;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public UUID getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(UUID userStoryId) {
        this.userStoryId = userStoryId;
    }

    public UUID getTaskTeamId() {
        return taskTeamId;
    }

    public void setTaskTeamId(UUID taskTeamId) {
        this.taskTeamId = taskTeamId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
