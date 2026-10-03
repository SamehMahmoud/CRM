package com.sameh.crm.backend.services;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.entities.User.UserStatus;
import com.sameh.crm.backend.repositories.UserRepository;

@Service
public class CrmUserDetailsService implements UserDetailsService {


    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    
    private UserRepository userRepository;

    @Autowired 
    public CrmUserDetailsService(UserRepository repository){
        this.userRepository = repository;
    }
    

    protected boolean isEmail(String input){
        if(input == null)
            return false;
        return EMAIL_PATTERN.matcher(input.trim()).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt =null;

        if(isEmail(username)){
            userOpt = this.userRepository.findByEmail(username);
        }else{
            userOpt = this.userRepository.findByUsername(username);
        }

        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid username or email"));

        User.UserStatus status = user.getStatus();

        boolean enabled = status == UserStatus.ACTIVE;

        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername()
                        , user.getPasswordHash(), enabled, enabled,enabled,enabled, Collections.emptyList());


        return details;
    }

}
