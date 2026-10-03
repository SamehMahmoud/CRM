package com.sameh.crm.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sameh.crm.backend.models.AuthenticationRequest;

@Service 
public class AuthenticationService {


    private AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public Authentication authenticate(AuthenticationRequest request){

        Authentication authToken = new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authToken);

        return authentication;
    }

    

}
