package com.wposs.scrum_back.taskteam.entity;

import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.team.entity.Team;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Column(name = "fk_team")
    private UUID teamId;
    @ManyToOne
    @JoinColumn(name = "fk_team",insertable = false,updatable = false)
    private Team team;

    @OneToMany(mappedBy = "taskTeam",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Board> boards;

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

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }
}
