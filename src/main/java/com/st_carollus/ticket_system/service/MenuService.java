package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.MenuRequest;
import com.st_carollus.ticket_system.model.dto.response.MenuResponse;
import com.st_carollus.ticket_system.model.entity.Menu;

import java.util.List;

public interface MenuService {
    MenuResponse create(MenuRequest request);
    List<MenuResponse> getAll();
    MenuResponse getById(String id);
    Menu getEntityByMenuCode(String menuCode);
    MenuResponse update(String id, MenuRequest request);
    void delete(String id);
}
