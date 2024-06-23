package com.elife.mandra.Web.RestControllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elife.mandra.Business.Services.AuthenticationService;
import com.elife.mandra.Business.Services.JwtService;
import com.elife.mandra.Web.Requests.AuthenticationDTO.AuthenticationUserDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // Injecting required services
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    // Constructor for dependency injection
    public AuthController(AuthenticationService authenticationService,
            JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    // Endpoint for user login (sign-in)
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationUserDTO> auth(Authentication authentication) {
        // Authenticate the user and generate the authenticated user DTO
        AuthenticationUserDTO authenticationUserDTO = this.authenticationService.login(authentication);
        // Generate a JWT cookie
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(jwtService.generateToken(authentication));
        // Return the response with the JWT cookie in the headers
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(authenticationUserDTO);
    }


    // Endpoint for user logout (sign-out)
    @PostMapping("/signout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // Generate a clean JWT cookie to remove the existing one
        ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();
        // Return the response with the clean JWT cookie in the headers
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .build();
    }

}
