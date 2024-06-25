package com.elife.mandra.Web.Requests.AuthenticationDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.Owner;

public record AuthenticationUserDTO(
        Long id,
        String email,
        List<String> roles) {

    public static AuthenticationUserDTO toAuthenticationUserDTO(Client client) {
        List<String> roles = client.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new AuthenticationUserDTO(client.getId(), client.getEmail(), roles);
    }

    public static AuthenticationUserDTO toAuthenticationUserDTO(Admin admin) {
        List<String> roles = admin.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new AuthenticationUserDTO(admin.getId(), admin.getEmail(), roles);
    }

    public static AuthenticationUserDTO toAuthenticationUserDTO(Owner owner) {
        List<String> roles = owner.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new AuthenticationUserDTO(owner.getId(), owner.getEmail(), roles);
    }
}
