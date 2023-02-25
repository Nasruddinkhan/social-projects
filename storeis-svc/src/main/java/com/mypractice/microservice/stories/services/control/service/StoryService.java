package com.mypractice.microservice.stories.services.control.service;

import com.mypractice.microservice.socialcore.dto.story.StoryDto;

import java.util.List;

public interface StoryService {
    StoryDto createStories(final StoryDto storyDto);

    List<StoryDto> findAllStories();
}
