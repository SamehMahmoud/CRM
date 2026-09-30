package com.sameh.crm.backend.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class RegistrationRequestTest {


    private Validator validator;

    @BeforeEach
    void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void shouldAcceptValidRequest(){
        System.out.println("Testing registration request");
        RegistrationRequest request = new RegistrationRequest();

        request.setUsername("sameh");
        request.setEmail("sameh@example.com");
        request.setPlainTextPassword("Password1!");
        request.setFirstName("Sameh");
        request.setLastName("Mahmoud");
        request.setPhone("01001234567");

        Set<ConstraintViolation<RegistrationRequest>> violationSet = this.validator.validate(request);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    void shouldRejectBlankUsername() {
        RegistrationRequest request = validRequest();
        request.setUsername("");

        Set<ConstraintViolation<RegistrationRequest>> violations =
                validator.validate(request);

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("username"))
        );
    }

    public static RegistrationRequest validRequest() {
        RegistrationRequest request = new RegistrationRequest();

        request.setUsername("sameh");
        request.setEmail("sameh@example.com");
        request.setPlainTextPassword("Password1!");
        request.setFirstName("Sameh");
        request.setLastName("Mahmoud");
        request.setPhone("01001234567");

        return request;
    }

}
