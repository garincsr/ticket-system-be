package com.st_carollus.ticket_system.model.dto.response;

import com.st_carollus.ticket_system.model.entity.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleMenuAccessResponse {
    private String id;
    private String roleCode;
    private Menu menu;
    private Boolean canView;
    private Boolean canCreate;
    private Boolean canEdit;
    private Boolean canDelete;
}
