package com.wposs.scrum_back.improvements.service;

import com.wposs.scrum_back.improvements.dto.ImprovementsDto;
import com.wposs.scrum_back.improvements.entity.Improvements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImprovementsService {
    List<ImprovementsDto> getAllImprovements();
    ImprovementsDto saveImprovements(ImprovementsDto improvementsDto);
    Optional<ImprovementsDto> getByIdimprovements(UUID improvementsId);
    Boolean deleteImprovements(UUID idImprovements);
}
