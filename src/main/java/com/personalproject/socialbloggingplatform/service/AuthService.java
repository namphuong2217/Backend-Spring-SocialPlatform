package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.dto.LoginResponse;
import com.personalproject.socialbloggingplatform.dto.RefreshTokenRequest;
import com.personalproject.socialbloggingplatform.dto.RegisterRequest;
import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.model.User;
import com.personalproject.socialbloggingplatform.repository.UserRepository;
import com.personalproject.socialbloggingplatform.security.CustomUserDetails;
import com.personalproject.socialbloggingplatform.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;

    // Task 1 helper function. Find user by email
    @Transactional(readOnly = true)
    public boolean emailExisted(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return true;
        else
            return false;
    }


    // Task 2 Register new user
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        // 1 hour of doing research
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        (org.springframework.security.core.userdetails.User) user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());
        if (user == null)
            throw new RecordNotFoundException("User name not found: " + principal.getUsername());
        return user;
    }

//    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
//        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
//        return LoginResponse.builder()
//                .loginToken(token)
//                .refreshToken(refreshTokenRequest.getRefreshToken())
//                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
//                .username(refreshTokenRequest.getUsername())
//                .build();
//    }

}
