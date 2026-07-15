package com.st_carollus.ticket_system.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RoleMenuAccessGroupedResponse {
    private RoleResponse role;
    private List<MenuAccessItem> menus;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MenuAccessItem {
        private String menuId;
        private String menuName;
        private String menuCode;
        private String menuUrl;
        private String icon;
        private Integer orderIndex;
        private Boolean isActive;
        private Boolean canView;
        private Boolean canCreate;
        private Boolean canEdit;
        private Boolean canDelete;
    }
}
