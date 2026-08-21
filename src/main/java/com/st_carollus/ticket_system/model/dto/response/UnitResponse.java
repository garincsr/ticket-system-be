package com.st_carollus.ticket_system.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UnitResponse {
    private String id;
    private String unitName;
}
