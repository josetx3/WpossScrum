package com.wposs.scrum_back.area.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaDto implements Serializable {

    @JsonProperty(value ="areaId", access = JsonProperty.Access.READ_ONLY)
    private UUID areaId;

    @JsonProperty(value = "areaName")
    @NotNull
    @NotEmpty
    @Size(max = 20,message = "excedió mas de 20 caracteres en el nombre del area")
    private String areaName;



    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
