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

    // Encodes JWT tokens
    private final JwtEncoder encoder;
    // Specifies the expiration time for the JWT token in seconds, fetched from
    // application properties
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    // Specifies the name of the cookie that will store the JWT token, fetched from
    // application properties
    @Value("${jwt.cookie-name}")
    private String jwtCookieName;

    public JwtServiceImpl(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    // After Basic authentication, the Authentication object passed to the
    // generateToken() method will contain all the necessary user information.
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = jwtExpiration;
        // the user roles are set to the scope claim
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
    }

    @Override
    public ResponseCookie generateJwtCookie(String jwt) {
        return ResponseCookie.from(jwtCookieName, jwt)
                .path("/")
                .maxAge(24 * 60 * 60) // 24 hours
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .build();
    }

    @Override
    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie.from(jwtCookieName, "")
                .path("/")
                .build();
    }

}
