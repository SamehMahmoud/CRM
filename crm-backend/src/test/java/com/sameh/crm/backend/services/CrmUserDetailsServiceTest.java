package com.sameh.crm.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sameh.crm.backend.entities.User;
import com.sameh.crm.backend.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class CrmUserDetailsServiceTest {


    @Mock 
    private UserRepository userRepository;


    @InjectMocks 
    private CrmUserDetailsService service;


    @Test 
    void shouldLoadUserByUsername(){
        
        User testUser = createTestUser();
        
        when(userRepository.findByUsername("sameh")).thenReturn(Optional.of(testUser));

        UserDetails details = this.service.loadUserByUsername("sameh");

        performAssertions(details);

        verify(userRepository).findByUsername("sameh");
        verify(userRepository, never()).findByEmail(anyString());    
    }

    @Test
    void shouldLoadUserByEmail(){

        User testUser = createTestUser();

        when(this.userRepository.findByEmail("sameh@example.com")).thenReturn(Optional.of(testUser));

        UserDetails details = this.service.loadUserByUsername("sameh@example.com");

        performAssertions(details);

        verify(userRepository).findByEmail("sameh@example.com");
        verify(userRepository,never()).findByUsername(anyString());

    }

    private void performAssertions(UserDetails details){
        assertNotNull(details);
        assertEquals("sameh", details.getUsername());
        assertEquals("hashed-password", details.getPassword());
        assertTrue(details.isEnabled());
    }




    private User createTestUser(){
        User user = new User();
        user.setUsername("sameh");
        user.setEmail("sameh@example.com");
        user.setPasswordHash("hashed-password");
        user.setStatus(User.UserStatus.ACTIVE);
        return user;
    }



    @Test
    void shouldThrowExceptionWhenUserDoesnotExist(){


        when(this.userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, ()->service.loadUserByUsername("unknown"));

    }

    @Test
    void shouldDisableSuspendedUser() {

        User user = new User();
        user.setUsername("sameh");
        user.setEmail("sameh@example.com");
        user.setPasswordHash("hashed-password");
        user.setStatus(User.UserStatus.SUSPENDED);

        when(userRepository.findByUsername("sameh"))
                .thenReturn(Optional.of(user));

        UserDetails result =
                service.loadUserByUsername("sameh");

        assertFalse(result.isEnabled());

        verify(userRepository).findByUsername("sameh");
    }



}
