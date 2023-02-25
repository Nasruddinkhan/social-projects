package com.mypractice.microservice.socialcore.dto.story;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mypractice.microservice.socialcore.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoryDto {
    @JsonProperty("story_id")
    private String id;

    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("type")
    private String type;
    @JsonProperty("likes")
    private List<String> likes;
    @JsonProperty("comments")
    private List<CommentDto> comments;
    @JsonProperty("shares")
    private List<String> shares;
    @JsonProperty("view_count")
    private int viewCount;

    @JsonProperty("url")
    private String url;
}
