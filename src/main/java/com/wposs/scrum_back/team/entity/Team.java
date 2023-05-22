package com.wposs.scrum_back.team.entity;

import com.wposs.scrum_back.area.entity.Area;
import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.improvements.entity.Improvements;
import com.wposs.scrum_back.sprint.entity.Sprint;
import com.wposs.scrum_back.subProject.entity.SubProject;
import com.wposs.scrum_back.taskteam.entity.TaskTeam;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "team", schema = "wposs")
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "team_id")
    private UUID teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "area_id")
    private UUID areaId;

    @ManyToOne
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    private Area area;

    @OneToMany(mappedBy = "team",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<TaskTeam> taskTeams;

    @OneToMany(mappedBy = "team",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<SubProject> subProjects;

    @OneToMany(mappedBy = "team",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Sprint> sprints;

    @OneToMany(mappedBy = "team",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Improvements> improvements;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "team_employee", schema = "wposs",
            joinColumns = @JoinColumn(name = "team_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "employee_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "employee_id"}, name = "uc_employee_team"))
    private List<Employee> employees;

    @OneToMany(mappedBy = "teamBoard",cascade = {CascadeType.DETACH,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Board> boards;

    public List<Improvements> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<Improvements> improvements) {
        this.improvements = improvements;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<TaskTeam> getTaskTeams() {
        return taskTeams;
    }

    public void setTaskTeams(List<TaskTeam> taskTeams) {
        this.taskTeams = taskTeams;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
