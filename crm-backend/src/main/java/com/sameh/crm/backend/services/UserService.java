package com.sameh.crm.backend.services;

import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.models.RegistrationRequest;
import com.sameh.crm.backend.models.RegistrationResponse;
import com.sameh.crm.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {


    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public RegistrationResponse registerUser(RegistrationRequest request){
        String username = request.getUsername();
        String email = request.getEmail();

        if(this.userRepository.existsByUsernameOrEmail(username, email)){
            throw new IllegalArgumentException("Username or email in use !");
        }

        String hashedPassword = this.passwordEncoder.encode(request.getPlainTextPassword());

        User user = new User();
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPasswordHash(hashedPassword);
        user.setEmail(email);
        user.setPhone(request.getPhone());
        user.setUsername(username);
        user.setStatus(User.UserStatus.ACTIVE);
        user.setCreatedAt(Instant.now());

        User presistedUser = this.userRepository.save(user);
        return new RegistrationResponse(presistedUser);

    }

}
