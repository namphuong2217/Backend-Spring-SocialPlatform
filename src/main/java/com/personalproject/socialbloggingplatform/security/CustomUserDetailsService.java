package com.personalproject.socialbloggingplatform.security;

import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

// User Details Service to handle User Details required by Spring security
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // check if user exists in database
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RecordNotFoundException("Username not found: " + username);
        }
        return new CustomUserDetails(user);
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), user.isEnabled(), true,
//                true, true,
//                getAuthorities("USER)"));
    }

    // used by JwtProvider
    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
               throw new RecordNotFoundException("User not found with id: " + id);
        else
            return new CustomUserDetails(user.get());
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), user.isEnabled(), true,
//                true, true,
//                getAuthorities("USER)"));
    }


    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
