package com.mypractice.microservice.stories.services.control.service;

import com.mypractice.microservice.socialcore.dto.story.StoryDto;
import com.mypractice.microservice.socialcore.util.MapperUtil;
import com.mypractice.microservice.stories.services.control.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StoryServiceImpl(StoryRepository repository) implements StoryService{
    @Override
    public StoryDto createStories(StoryDto storyDto) {
        return null;
    }

    @Override
    public List<StoryDto> findAllStories() {
        return MapperUtil.mapAll(repository.findAll(), StoryDto.class);
    }
}
