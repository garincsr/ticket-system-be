package com.st_carollus.ticket_system.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoleResponse {
    private String id;
    private String roleName;
    private String roleCode;
    private Boolean isActive;
}
