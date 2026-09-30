package com.sameh.crm.backend.services;

import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.models.RegistrationRequest;
import com.sameh.crm.backend.models.RegistrationRequestTest;
import com.sameh.crm.backend.models.RegistrationResponse;
import com.sameh.crm.backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UserRepository userRepository;

    //@Mock
    @InjectMocks
    private UserService service;

    @Test
    void shouldRegisterUser() {

        RegistrationRequest request = RegistrationRequestTest.validRequest();

        when(userRepository.existsByUsernameOrEmail(
                request.getUsername(),
                request.getEmail()))
                .thenReturn(false);

        when(encoder.encode(request.getPlainTextPassword()))
                .thenReturn("hashed-password");

        User savedUser = new User();
        savedUser.setId("user-id");
        savedUser.setUsername(request.getUsername());
        savedUser.setEmail(request.getEmail());
        savedUser.setPasswordHash("hashed-password");
        savedUser.setStatus(User.UserStatus.ACTIVE);
        savedUser.setCreatedAt(Instant.now());

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        RegistrationResponse response = service.registerUser(request);

        assertEquals("user-id", response.getId());
        assertEquals("sameh", response.getUsername());
        assertEquals("sameh@example.com", response.getEmail());
        //assertEquals("hashed-password", response.getPasswordHash());
        //assertEquals(User.UserStatus.ACTIVE, response.getStatus());

        verify(encoder)
                .encode(request.getPlainTextPassword());

        verify(userRepository)
                .save(any(User.class));
    }

}
