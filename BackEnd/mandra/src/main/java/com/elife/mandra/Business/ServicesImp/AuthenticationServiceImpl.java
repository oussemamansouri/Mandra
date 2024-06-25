package com.elife.mandra.Business.ServicesImp;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.elife.mandra.Business.Services.AuthenticationService;
import com.elife.mandra.DAO.Entities.Client;
import com.elife.mandra.DAO.Entities.Admin;
import com.elife.mandra.DAO.Entities.Owner;
import com.elife.mandra.Web.Requests.AuthenticationDTO.AuthenticationUserDTO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public AuthenticationUserDTO login(Authentication authentication) {
        // Retrieve the user principal from the authentication object
        UserDetails user = (UserDetails) authentication.getPrincipal();

        if (user instanceof Client) {
            return AuthenticationUserDTO.toAuthenticationUserDTO((Client) user);
        } else if (user instanceof Admin) {
            return AuthenticationUserDTO.toAuthenticationUserDTO((Admin) user);
        } else if (user instanceof Owner) {
            return AuthenticationUserDTO.toAuthenticationUserDTO((Owner) user);
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }
}
