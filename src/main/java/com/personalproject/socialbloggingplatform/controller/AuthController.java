package com.personalproject.socialbloggingplatform.controller;

import com.personalproject.socialbloggingplatform.dto.LoginRequest;
import com.personalproject.socialbloggingplatform.dto.LoginResponse;
import com.personalproject.socialbloggingplatform.dto.RefreshTokenRequest;
import com.personalproject.socialbloggingplatform.dto.RegisterRequest;
import com.personalproject.socialbloggingplatform.exception.ResourceAlreadyExistsException;
import com.personalproject.socialbloggingplatform.security.CustomUserDetails;
import com.personalproject.socialbloggingplatform.security.JwtProvider;
import com.personalproject.socialbloggingplatform.service.AuthService;
import com.personalproject.socialbloggingplatform.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    // Spring Security implementation
    @Autowired
    AuthenticationManager authenticationManager;

    // Spring Security implementation
    @Autowired
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest){
        if(authService.emailExisted(registerRequest.getEmail()))
            throw new ResourceAlreadyExistsException("Email has already been registered");
        authService.register(registerRequest);
        return ResponseEntity.ok("User has been registered successfully");
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest){

        // authenticate username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // no exception means authentication valid, set authentication into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // response with JWT
        String jwt = jwtProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return  LoginResponse.builder()
                .loginToken(jwt)
                .username(loginRequest.getUsername())
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }

    @PostMapping("/refresh/token")
    public LoginResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return LoginResponse.builder()
                .loginToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Logout and refresh token deleted successfully");
    }



}
