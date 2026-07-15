package com.st_carollus.ticket_system.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String email;
    private String fullName;
    private RoleMenuAccessGroupedResponse roleMenuAccess;
}
