package com.personalproject.socialbloggingplatform.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    // secret key reserved by server, hardcode for the project
    @Value("${jwt.secret.key}")
    private String JWT_SECRET;
//    private final String JWT_SECRET = "8DD9E49408D12042E896EB7CCF000D6926104BD146231C9DA9D08AB1CB867A506740ED16835B0E42E4BAB7C992D3B708D593DF4FF320002E8F34058C1B6FD731";

    // hardcode expired time of token
    @Value("${jwt.expiration.time}")
    private Long JWT_EXPIRATION;
//    private final long JWT_EXPIRATION = 900000;

    // create JWT from user details
    public String generateToken(CustomUserDetails userDetails){
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getUserId()))
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // for RefreshToken
    public String generateTokenWithUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setExpiration(Date.from(Instant.now().plusMillis(JWT_EXPIRATION)))
                .compact();
    }

    // retrieve user info from jwt
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e){
            log.error("Invalid Authentication Token");
        }
        catch (ExpiredJwtException e){
            log.error("Authentication Token Expired");
        }
        catch (UnsupportedJwtException e){
            log.error("Unsupported Authentication Token");
        }
        catch (IllegalArgumentException e){
            log.error("JWT claims string is empty");
        } catch (Exception e){
            log.error("JWT Exception");
        }
        return false;
    }

    public Long getJwtExpirationInMillis() {
        return JWT_EXPIRATION;
    }


}
