package com.personalproject.socialbloggingplatform.repository;

import com.personalproject.socialbloggingplatform.model.Community;
import com.personalproject.socialbloggingplatform.model.Post;
import com.personalproject.socialbloggingplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findAllByCommunity(Community community);
    List<Post> findByUser(User user);

}
