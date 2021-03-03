package com.personalproject.socialbloggingplatform.repository;


import com.personalproject.socialbloggingplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    void deleteById(Long id);

    Optional<User> findByEmail(String email);

    User findByUsername(String username);
}
