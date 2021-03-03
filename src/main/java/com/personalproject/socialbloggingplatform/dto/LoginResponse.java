package com.personalproject.socialbloggingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String loginToken;
    private String username;
    private String refreshToken;
    private Instant expiresAt;
}
