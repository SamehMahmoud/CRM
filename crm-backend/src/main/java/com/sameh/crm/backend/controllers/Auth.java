package com.sameh.crm.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sameh.crm.backend.models.AuthenticationRequest;
import com.sameh.crm.backend.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class Auth {



    private AuthenticationService authenticationService;


    @Autowired 
    public Auth(AuthenticationService service){
        this.authenticationService = service;
    }

    @PostMapping("/login")
    public Authentication login(@Valid @RequestBody AuthenticationRequest request){

        return this.authenticationService.authenticate(request);

    }


}
