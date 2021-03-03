package com.personalproject.socialbloggingplatform.controller;

import com.personalproject.socialbloggingplatform.dto.PostRequest;
import com.personalproject.socialbloggingplatform.dto.PostResponse;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Mapping	HTTP Method	Method Name
//        /api/posts	POST	createPost
//        /api/posts/	GET	getAllPosts
//        /api/posts/{id}	GET	getPost
//        /api/posts/by-subreddit/{id}	GET	getPostsBySubreddit
//        /api/posts/by-user/{name}	GET	getPostsByUsername

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Create new post successful");
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse postResponse = postService.getPost(id);
        if (postResponse == null)
            throw new RecordNotFoundException("Post not found with id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    @GetMapping("by-community/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
        List<PostResponse> posts = postService.getPostsByCommunity(id);
        if (posts == null)
            throw new RecordNotFoundException("Post not found with community id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
        List<PostResponse> posts = postService.getPostsByUsername(username);
        if (posts == null)
            throw new RecordNotFoundException("Post not found with username: " + username);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

}
