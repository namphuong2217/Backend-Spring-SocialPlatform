package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.dto.CommentDto;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.mapper.CommentMapper;
import com.personalproject.socialbloggingplatform.model.Comment;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.CommentRepository;
import com.personalproject.socialbloggingplatform.repository.PostRepository;
import com.personalproject.socialbloggingplatform.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
//    private final MailContentBuilder mailContentBuilder;
//    private final MailService mailService;

    public void save(CommentDto commentDto) {
        Optional<Post> post = postRepository.findById(commentDto.getPostId());
        if(!post.isPresent())
            throw new RecordNotFoundException("Post not found with id: " + commentDto.getPostId());
        Comment comment = commentMapper.map(commentDto, post.get(), authService.getCurrentUser());
        commentRepository.save(comment);
//        String notificationMessage = mailContentBuilder.build(authService.getCurrentUser() + " commented on you post"
//                + post.getUrl());
//        mailService.sendMail(new NotificationEmail(post.getUser().getUsername()+" commented on your post",
//                post.getUser().getEmail(), notificationMessage));
    }

    public List<CommentDto> getAllCommentsOfPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return commentRepository.findAllByPost(post.get()).stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsOfUser(String username, User user) {
        List<Comment> comments = commentRepository.findAllByUser(user);
        return comments.stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }

}
