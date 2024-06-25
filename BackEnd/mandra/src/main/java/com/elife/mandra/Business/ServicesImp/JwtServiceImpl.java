package com.elife.mandra.Business.ServicesImp;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.elife.mandra.Business.Services.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder encoder;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.cookie-name}")
    private String jwtCookieName;

    public JwtServiceImpl(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String generateToken(Authentication authentication) {
        try {
            Instant now = Instant.now();
            long expiry = jwtExpiration;
            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(authentication.getName())
                    .claim("scope", scope)
                    .build();
            return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    @Override
    public ResponseCookie generateJwtCookie(String jwt) {
        try {
            return ResponseCookie.from(jwtCookieName, jwt)
                    .path("/")
                    .maxAge(24 * 60 * 60) // 24 hours
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT cookie", e);
        }
    }

    @Override
    public ResponseCookie getCleanJwtCookie() {
        try {
            return ResponseCookie.from(jwtCookieName, "")
                    .path("/")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clean JWT cookie", e);
        }
    }

}
