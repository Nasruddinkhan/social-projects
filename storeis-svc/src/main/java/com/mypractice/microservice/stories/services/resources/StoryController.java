package com.mypractice.microservice.stories.services.resources;

import com.mypractice.microservice.socialcore.dto.story.StoryDto;
import com.mypractice.microservice.stories.services.control.service.StoryService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
@Observed(name = "stories")
public class StoryController {
    private final StoryService storyService;
    @GetMapping
    public ResponseEntity<List<StoryDto>> findAllStories(){
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }
}
