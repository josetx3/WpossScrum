package com.wposs.scrum_back.sprintemployee.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class SprintEmployeePk implements Serializable {
    public SprintEmployeePk(UUID employeeId, UUID sprintId) {
        this.employeeId = employeeId;
        this.sprintId = sprintId;
    }

    @Column(name = "fk_employee_id",nullable = false)
    @Type(type = "pg-uuid")
    private UUID employeeId;
    @Column(name = "fk_Sprint_id")
    @Type(type = "pg-uuid")
    private UUID sprintId;

    public SprintEmployeePk() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintEmployeePk)) return false;
        SprintEmployeePk that = (SprintEmployeePk) o;
        return Objects.equals(getSprintId(), that.getSprintId()) &&
                Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSprintId(), getEmployeeId());
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public UUID getSprintId() {
        return sprintId;
    }

    public void setSprintId(UUID sprintId) {
        this.sprintId = sprintId;
    }
}
