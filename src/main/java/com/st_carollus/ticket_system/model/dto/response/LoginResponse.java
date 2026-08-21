package com.st_carollus.ticket_system.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String token;
    private String tokenType;
    private String username;
    private String fullName;
    private String roleCode;
//    private RoleMenuAccessGroupedResponse roleMenuAccess;
}
