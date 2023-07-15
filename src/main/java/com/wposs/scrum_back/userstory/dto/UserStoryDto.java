package com.wposs.scrum_back.userstory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class UserStoryDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID userStoryId;

    @NotNull(message = "La historia de usuario no debe ser null")
    @NotEmpty
    @Size(max = 100,message = "La HU debe no debe contener mas 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z \\d ]+$",message = "El nombre de la historia solo debe contener letras y espacios")
    private String userStoryName;

    @Pattern(regexp = "^[a-zA-Z ]+$",message = "El codigo de la historia solo debe contener letras y numeros")
    private String userStoryCode;
    @NotNull(message = "historia de usuario no debe ser null")
    @Max(value = 999,message = "Los puntos no pueden exceder los 3 dijitos")
    private Integer userStoryScore;

    private String userStoryArchive;
    @NotNull(message = "el estado no de HU no puede ser null")
    @Max(value = 6,message = "sobre los estados de las HU")
    private Long userStoryStateId;
    @JsonProperty(value = "userStoryStateName",access = JsonProperty.Access.READ_ONLY)
    private String userStoryStateName;
    @JsonProperty(value = "subProjectId")
    private UUID subProjectId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "la fecha maxima no puede ser null")
    private Date fechaMaxima;

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getUserStoryStateName() {
        return userStoryStateName;
    }

    public void setUserStoryStateName(String userStoryStateName) {
        this.userStoryStateName = userStoryStateName;
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

    public String getUserStoryCode() {
        return userStoryCode;
    }

    public void setUserStoryCode(String userStoryCode) {
        this.userStoryCode = userStoryCode;
    }
}
