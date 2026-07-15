package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    @NotBlank(message = "Role name is required")
    private String roleName;

    @NotBlank(message = "Role code is required")
    private String roleCode;

    private Boolean isActive;
}
