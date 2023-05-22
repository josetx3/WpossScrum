package com.wposs.scrum_back.userstorystatus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStoryStatusDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userStoryStateId;

    @NotNull(message = "El estado no puede ser null")
    @NotEmpty
    @Size(max = 20,message = "supera el la cantidad de caracteres en el nombre del estado")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "ingrese solo letras en el campo de Estado")
    private String userStoryStateName;

    public Long getUserStoryStateId() {
        return userStoryStateId;
    }

    public void setUserStoryStateId(Long userStoryStateId) {
        this.userStoryStateId = userStoryStateId;
    }

    public String getUserStoryStateName() {
        return userStoryStateName;
    }

    public void setUserStoryStateName(String userStoryStateName) {
        this.userStoryStateName = userStoryStateName;
    }
}
