package com.sameh.crm.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class SecurityConfigs {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security){
        security.authorizeHttpRequests(
                auth-> auth.requestMatchers("/users").permitAll().anyRequest().authenticated()
        );
        security.csrf(csrf-> csrf.disable());
        return security.build();
    }


 


}
