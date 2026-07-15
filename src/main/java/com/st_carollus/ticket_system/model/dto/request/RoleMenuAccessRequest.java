package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleMenuAccessRequest {
    @NotBlank(message = "Role Code is required")
    private String roleCode;

    @NotBlank(message = "Menu Code is required")
    private String menuCode;

    @NotBlank(message = "Can View is required")
    private Boolean canView;

    @NotBlank(message = "Can Create is required")
    private Boolean canCreate;

    @NotBlank(message = "Can Edit is required")
    private Boolean canEdit;

    @NotBlank(message = "Can Delete is required")
    private Boolean canDelete;
}
