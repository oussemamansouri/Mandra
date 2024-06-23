package com.elife.mandra.Business.ServicesImp;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.elife.mandra.Business.Services.AuthenticationService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.Web.Requests.AuthenticationDTO.AuthenticationUserDTO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{


    @Override
    public AuthenticationUserDTO login(Authentication authentication) {
        // Retrieve the user principal from the authentication object after basic authentication
        Client client = (Client) authentication.getPrincipal();
        // Convert the User entity to AuthenticationUserDTO and return it
        return AuthenticationUserDTO.toAuthenticationUserDTO(client);
    }

}
