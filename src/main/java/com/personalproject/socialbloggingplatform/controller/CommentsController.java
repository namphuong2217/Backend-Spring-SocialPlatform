package com.personalproject.socialbloggingplatform.controller;

import com.personalproject.socialbloggingplatform.dto.CommentDto;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.PostRepository;
import com.personalproject.socialbloggingplatform.repository.UserRepository;
import com.personalproject.socialbloggingplatform.service.CommentService;
import lombok.AllArgsConstructor;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-postId/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfPost(@PathVariable Long postId){
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent())
            throw new RecordNotFoundException("Post not found with id: " + postId.toString());
        List<CommentDto> comments = commentService.getAllCommentsOfPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfUser(@PathVariable String userName){
        User user = userRepository.findByUsername(userName);
        if (user == null)
            throw new RecordNotFoundException("Username not found: " + userName);
        List<CommentDto> comments = commentService.getAllCommentsOfUser(userName, user);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }
}
