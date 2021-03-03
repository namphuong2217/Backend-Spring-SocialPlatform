package com.personalproject.socialbloggingplatform.service;

import com.personalproject.socialbloggingplatform.exception.RecordNotFoundException;
import com.personalproject.socialbloggingplatform.model.RefreshToken;
import com.personalproject.socialbloggingplatform.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new RecordNotFoundException("Invalid Refresh Token"));
    }

    public void deleteToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
