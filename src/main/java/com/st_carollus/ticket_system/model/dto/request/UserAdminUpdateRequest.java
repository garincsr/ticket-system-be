package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAdminUpdateRequest {

    private String username;

    @Email(message = "Invalid email format")
    private String email;

    private String fullName;

    private String password;

    private String phone;

    private Boolean isActive;

    private Boolean isVerified;

    private String roleCode;

    private String unitName;
}
