package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Password is required")
    private String password;

    private String phone;

    @NotBlank(message = "Unit name is required")
    private String unitName;

    private Boolean isActive;

    @NotBlank(message = "Role Code is required")
    private String roleCode;
}
