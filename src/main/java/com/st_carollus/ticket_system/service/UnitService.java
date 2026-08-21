package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.response.UnitResponse;
import com.st_carollus.ticket_system.model.entity.Unit;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UnitService {
    UnitResponse create(String unitName);
    Page<UnitResponse> getAll(String search, String sortBy, String direction, int page, int size);
    UnitResponse getById(String id);
    Unit getEntityByUnitName(String unitName);
    UnitResponse update(String id, String unitName);
    void delete(String id);
}
