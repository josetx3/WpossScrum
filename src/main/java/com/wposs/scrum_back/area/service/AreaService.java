package com.wposs.scrum_back.area.service;

import com.wposs.scrum_back.area.dto.AreaDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AreaService {
    List<AreaDto> getAllArea();
    Optional<AreaDto> getAreaId(UUID idAre);
    AreaDto saveArea(AreaDto areaDto);
    AreaDto updateArea(UUID idArea,AreaDto areaDto);
    Boolean existAreaByName(String areName);
    Boolean deleteArea(UUID idArea);
}
