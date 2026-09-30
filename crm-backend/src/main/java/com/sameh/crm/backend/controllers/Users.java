package com.sameh.crm.backend.controllers;

import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.models.RegistrationRequest;
import com.sameh.crm.backend.models.RegistrationResponse;
import com.sameh.crm.backend.services.UserService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/users")
public class Users {


    private UserService userService;

    @Autowired
    public Users(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request){

        RegistrationResponse response = this.userService.registerUser(request);

        return ResponseEntity.accepted().body(response);

    }



}
