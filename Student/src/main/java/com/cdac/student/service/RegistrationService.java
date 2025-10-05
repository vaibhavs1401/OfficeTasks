package com.cdac.student.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public interface RegistrationService {

    /** Immutable DTO for registration */
    record RegisterRequest(
             @NotBlank @Email String email,
            @NotBlank @Size(min = 6, max = 100) String password,
            @NotBlank String name,
            @Positive Integer standard,         // can be null, service will default
            @Positive Integer age,              // can be null, service will default
            String rollNo                        // can be null, service will auto-generate
    ) {}

    /**
     * Registers a new user:
     *  - creates UserAccount with BCrypt password
     *  - assigns ROLE_STUDENT
     *  - creates linked Student profile
     * @return the created UserAccount id
     */
    Long registerStudent(RegisterRequest request);
}


