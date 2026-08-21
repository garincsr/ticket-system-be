package com.st_carollus.ticket_system.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String unitName;
    private Boolean isActive;
    private Boolean isVerified;
    private String roleCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}
