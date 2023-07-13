package com.wposs.scrum_back.employe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeDto {

    @JsonProperty(value ="employeeId")
    private UUID employeeId;

    @JsonProperty(value = "employeeName")
    @NotNull(message = "El nombre del Empleado no puede ser null")
    @NotEmpty
    @Size(max = 100,message = "el nombre del empleado no puede sobre pasar los 100 carateres")
    @Pattern(regexp = "^[a-zA-Z\\s ]+$",message = "El nombre del empleado solo se admiten letras y espacios")
    private String employeeName;

    @JsonProperty(value = "employeeCharge")
    @NotNull(message = "El cargo del Empleado no puede ser null")
    @NotEmpty
    @Size(max = 100,message = "el cargo del empleado no puede sobre pasar los 100 carateres")
    @Pattern(regexp = "^[a-zA-Z\\s ]+$",message = "El cargo del empleado solo se admiten letras y espacios")
    private String employeeCharge;

    @JsonProperty(value = "employeeEmail")
    @NotNull(message = "El email del Empleado no puede ser null")
    @NotEmpty
    @Size(max = 100,message = "el email del empleado no puede sobre pasar los 100 carateres")
    @Email(message = "email mal estructurado")
    private String employeeEmail;

    @JsonProperty(value = "employeePassword")
    @NotNull(message = "La contrasena del Empleado no puede ser vacia")
    @NotEmpty
    @Size(max = 64,message = "el password del empleado no puede exceder los 20 caracteres")
    private String employeePassword;
    @JsonProperty(value = "employeeKnowledge")
    @NotNull(message = "El conocimiento del Empleado no puede ser null")
    @NotEmpty
    @Size(max = 200,message = "El conocimieno del empleado no puede sobre pasar los 200 caracteres")
    @Pattern(regexp = "[a-zA-Z\\s]+",message = "El campo employeeKnowledge solo se admiten letras y espacios")
    private String employeeKnowledge;

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
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

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
