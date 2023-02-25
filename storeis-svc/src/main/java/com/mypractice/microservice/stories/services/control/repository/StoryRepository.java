package com.mypractice.microservice.stories.services.control.repository;

import com.mypractice.microservice.stories.services.entity.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {
}
