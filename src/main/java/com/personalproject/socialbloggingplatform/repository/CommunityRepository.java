package com.personalproject.socialbloggingplatform.repository;

import com.personalproject.socialbloggingplatform.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Community findByCommunityName(String communityName);
}
