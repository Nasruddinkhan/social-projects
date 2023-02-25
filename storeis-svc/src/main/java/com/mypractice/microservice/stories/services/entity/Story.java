package com.mypractice.microservice.stories.services.entity;

import com.mypractice.microservice.socialcore.dto.comment.CommentDto;
//import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Story {
  // @Id
    private String id;
    private String userId;
    private String title;
    private String content;
    private String url;
    private String type;
    private Date createdAt;
    private Date updatedAt;
    private List<String> likes;
    private List<CommentDto> comments;
    private List<String> shares;
    private int viewCount;
}
