package com.wposs.scrum_back.sprintemployee.entity;

import com.wposs.scrum_back.employe.entity.Employee;
import com.wposs.scrum_back.sprint.entity.Sprint;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sprints_employee",schema = "wposs")
public class SprintEmployee {
    @EmbeddedId
    private SprintEmployeePk id;
    @Column(name = "percentage",nullable = false)
    private Double percentage;
    @Column(name = "daysLeave",nullable = false)
    private Integer daysLeave;
    @Column(name = "Observations",nullable = false)
    private String observations;
    private Double percentageFinal;

    //private UUID idEmployee;
    //private UUID idSprint;


    @ManyToOne
    @JoinColumn(name = "fk_Sprint_id",insertable = false,updatable = false)
    private Sprint sprint;
    @ManyToOne
    @JoinColumn(name = "fk_employee_id",insertable = false,updatable = false)
    private Employee employee;

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getDaysLeave() {
        return daysLeave;
    }

    public void setDaysLeave(Integer daysLeave) {
        this.daysLeave = daysLeave;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SprintEmployeePk getId() {
        return id;
    }

    public void setId(SprintEmployeePk id) {
        this.id = id;
    }

    public Double getPercentageFinal() {
        return percentageFinal;
    }

    public void setPercentageFinal(Double percentageFinal) {
        this.percentageFinal = percentageFinal;
    }
}
