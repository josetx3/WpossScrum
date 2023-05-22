package com.wposs.scrum_back.userstory.service;

import com.wposs.scrum_back.userstory.dto.UserStoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserStoryService {
    List<UserStoryDto> getAllUserStory();
    List<UserStoryDto> getAllUserStoryToSubProject(UUID idSubProject);
    Optional<UserStoryDto> getUserStoryById(UUID idUserStory);
    UserStoryDto saveUserStory(UserStoryDto userStoryDto);
    UserStoryDto updateUserStory(UUID idUserStory,UserStoryDto userStoryDto);
    List<UserStoryDto> getAllUserStoryToTeam(UUID idTeam);
}
