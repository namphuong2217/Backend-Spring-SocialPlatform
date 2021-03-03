package com.personalproject.socialbloggingplatform.repository;

import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
