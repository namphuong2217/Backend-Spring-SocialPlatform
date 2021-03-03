package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.dto.PostRequest;
import com.personalproject.socialbloggingplatform.dto.PostResponse;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.mapper.PostMapper;
import com.personalproject.socialbloggingplatform.model.Community;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.CommunityRepository;
import com.personalproject.socialbloggingplatform.repository.PostRepository;
import com.personalproject.socialbloggingplatform.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommunityRepository communityRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        Community community = communityRepository.findByCommunityName(postRequest.getCommunityName());
        if (community == null)
            throw new RecordNotFoundException("Community not found: " + postRequest.getCommunityName());
        else
            postRepository.save(postMapper.map(postRequest, community, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent())
            return null;
        return postMapper.mapToDto(post.get());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByCommunity(Long id) {
        Optional<Community> community = communityRepository.findById(id);
        if (community.isPresent()) {
            List<Post> posts = postRepository.findAllByCommunity(community.get());
            return posts.stream().map(postMapper::mapToDto).collect(toList());
        }
        return null;

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            return null;
        List<Post> posts = postRepository.findByUser(user);
        if(posts==null)
            return null;
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }
}
