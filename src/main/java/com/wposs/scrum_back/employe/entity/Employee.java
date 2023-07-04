package com.wposs.scrum_back.employe.entity;

import com.wposs.scrum_back.board.entity.Board;
import com.wposs.scrum_back.sprint.entity.Sprint;
import com.wposs.scrum_back.sprintemployee.entity.SprintEmployee;
import com.wposs.scrum_back.team.entity.Team;
import com.wposs.scrum_back.area.entity.Area;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee", schema = "wposs")
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "employee_name", nullable = false, length = 100)
    private String employeeName;

    @Column(name = "employee_charge", nullable = false, length = 100)
    private String employeeCharge;

    @Column(name = "employee_email", nullable = false, length = 100, unique = true)
    private String employeeEmail;

    @Column(name = "employee_password", nullable = false, length = 64)
    private String employeePassword;

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    @Column(name = "employee_knowledge", nullable = false, length = 200)
    private String employeeKnowledge;

    
    @ManyToMany(mappedBy = "employees")
    private List<Area> areas;

    @ManyToMany(mappedBy = "employees")
    private List<Team> teams;

    @OneToMany(mappedBy = "employee")
    private List<Board> boards;

    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REMOVE})
    private List<SprintEmployee> sprintEmployees;

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public List<SprintEmployee> getSprintEmployees() {
        return sprintEmployees;
    }

    public void setSprintEmployees(List<SprintEmployee> sprintEmployees) {
        this.sprintEmployees = sprintEmployees;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCharge() {
        return employeeCharge;
    }

    public void setEmployeeCharge(String employeeCharge) {
        this.employeeCharge = employeeCharge;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeKnowledge() {
        return employeeKnowledge;
    }

    public void setEmployeeKnowledge(String employeeKnowledge) {
        this.employeeKnowledge = employeeKnowledge;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }



}
