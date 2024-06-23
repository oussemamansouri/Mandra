package com.elife.mandra.Business.Services;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;

public interface JwtService {


    String generateToken(Authentication authentication);
    ResponseCookie generateJwtCookie(String jwt);
    ResponseCookie getCleanJwtCookie();

}
