package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSelfUpdateRequest {

    private String fullName;

    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Current password is required")
    private String currentPassword;

    private String newPassword;
}
