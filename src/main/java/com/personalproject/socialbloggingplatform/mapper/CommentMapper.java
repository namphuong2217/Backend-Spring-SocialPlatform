package com.personalproject.socialbloggingplatform.mapper;

import com.personalproject.socialbloggingplatform.dto.CommentDto;
import com.personalproject.socialbloggingplatform.model.Comment;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CommentMapper {

//    @Mapping(target = "user", source = "user")
//    @Mapping(target = "post", source = "post")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
//    @Mapping(target = "text", source = "commentDto.text")
    public Comment map(CommentDto commentDto, Post post, User user){
        return Comment.builder()
                .text(commentDto.getText())
                .post(post)
                .user(user)
                .createdDate(Instant.now())
                .build();
    };

//    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
//    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    public CommentDto mapToDto(Comment comment){
        return CommentDto.builder()
                .commentId(comment.getId())
                .text(comment.getText())
                .postId(comment.getPost().getPostId())
                .userName(comment.getUser().getUsername())
                .createdDate(Instant.now())
                .build();
    };



}
