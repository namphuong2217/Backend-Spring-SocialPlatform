package com.personalproject.socialbloggingplatform.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.personalproject.socialbloggingplatform.dto.PostRequest;
import com.personalproject.socialbloggingplatform.dto.PostResponse;
import com.personalproject.socialbloggingplatform.model.Community;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.CommentRepository;
import com.personalproject.socialbloggingplatform.repository.VoteRepository;
import com.personalproject.socialbloggingplatform.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;

//@Mapper(componentModel = "spring")
@Component
public class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

//    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
//    @Mapping(target = "community", source = "community")
//    @Mapping(target = "user", source = "user")
//    @Mapping(target = "description", source = "postRequest.description")
    public Post map(PostRequest postRequest, Community community, User user){
        return Post.builder().postName(postRequest.getPostName())
                .url(postRequest.getUrl())
                .description(postRequest.getDescription())
                .user(user)
                .community(community)
                .createdDate(Instant.now())
                .voteCount(0)
                .build();
    };

//    @Mapping(target = "postId", source = "postId")
//    @Mapping(target = "postName", source = "postName")
//    @Mapping(target = "description", source = "description")
//    @Mapping(target = "url", source = "url")
//    @Mapping(target = "communityName", source = "community.name")
//    @Mapping(target = "userName", source = "user.username")
    public PostResponse mapToDto(Post post){
        return PostResponse.builder()
                .postId(post.getPostId())
                .postName(post.getPostName())
                .url(post.getUrl())
                .description(post.getDescription())
                .communityName("/community/" + post.getCommunity().getCommunityName())
                .userName(post.getUser().getUsername())
                .voteCount(post.getVoteCount())
                .commentCount(commentCount(post))
                .duration(getDuration(post))
                .build();
    };

    Integer commentCount(Post post){
        return commentRepository.findAllByPost(post).size();
    }

    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}
