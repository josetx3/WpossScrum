package com.wposs.scrum_back.scoresprintdays.service;


import com.wposs.scrum_back.scoresprintdays.dto.ScoringSprintDaysDto;

import java.util.UUID;

public interface ScoringSprintDaysService {
    ScoringSprintDaysDto updateScoreSpring(UUID idSprint, ScoringSprintDaysDto scoringSprintDaysDto );

}
