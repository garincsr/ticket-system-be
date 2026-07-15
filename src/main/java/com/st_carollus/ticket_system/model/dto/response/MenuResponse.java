package com.st_carollus.ticket_system.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuResponse {
    private String menuName;
    private String menuCode;
    private String menuUrl;
    private String icon;
    private Integer orderIndex;
    private Boolean isActive;
}
