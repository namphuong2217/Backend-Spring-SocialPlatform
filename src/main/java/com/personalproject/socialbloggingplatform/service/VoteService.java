package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.dto.VoteDto;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.exception.ResourceAlreadyExistsException;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.Vote;
import com.personalproject.socialbloggingplatform.model.VoteType;
import com.personalproject.socialbloggingplatform.repository.PostRepository;
import com.personalproject.socialbloggingplatform.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Optional<Post> post = postRepository.findById(voteDto.getPostId());
        if (!post.isPresent())
            throw new RecordNotFoundException("Post not found with id: " + voteDto.getPostId());
        Optional<Vote> votePostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post.get(), authService.getCurrentUser());
        if (votePostByUser.isPresent() && votePostByUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new ResourceAlreadyExistsException("You have already " + voteDto.getVoteType() + "d this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) { //VoteType.UPVOTE
            post.get().setVoteCount(post.get().getVoteCount() + 1);
        } else
            post.get().setVoteCount(post.get().getVoteCount() - 1);
        voteRepository.save(mapToVote(voteDto, post.get()));
        postRepository.save(post.get());
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder().voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}
