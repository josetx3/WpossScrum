package com.wposs.scrum_back.userstorystatus.service;

import com.wposs.scrum_back.userstorystatus.dto.UserStoryStatusDto;

import java.util.List;

public interface UserStoryStatusService {
    List<UserStoryStatusDto> gatAll();
    UserStoryStatusDto saveStatus(UserStoryStatusDto userStoryStatusDto);
    UserStoryStatusDto updateUserStoryStatus(Long idStatus, UserStoryStatusDto userStoryStatusDto);
    Boolean deleteProducto(Long idStatus);
}
