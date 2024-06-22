package com.elife.mandra.Web.Requests.AuthenticationDTO;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.elife.mandra.DAO.Entities.Client;

public record AuthenticationUserDTO(
        Long id,
        String email,
        List<String> roles) {
    public static AuthenticationUserDTO toAuthenticationUserDTO(Client client) {
        List<String> roles = client.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();
        return new AuthenticationUserDTO(client.getId(), client.getEmail(), roles);
    }
}
