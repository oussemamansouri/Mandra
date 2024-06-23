package com.elife.mandra.Business.Services;

import org.springframework.security.core.Authentication;

import com.elife.mandra.Web.Requests.AuthenticationDTO.AuthenticationUserDTO;

public interface AuthenticationService {

    //login operation
    AuthenticationUserDTO login(Authentication authentication);

}
