package com.st_carollus.ticket_system.model.dto.request;

import com.st_carollus.ticket_system.model.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Password is required")
    private String passwordHash;

    private Boolean isActive;

    @NotBlank(message = "Role is required")
    private Role role;
}
