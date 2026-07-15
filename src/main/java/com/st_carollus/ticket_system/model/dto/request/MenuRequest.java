package com.st_carollus.ticket_system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    @NotBlank(message = "Menu name is required")
    private String menuName;

    @NotBlank(message = "Menu code is required")
    private String menuCode;

    private String menuUrl;

    private String icon;

    private Integer orderIndex;

    private Boolean isActive;
}
