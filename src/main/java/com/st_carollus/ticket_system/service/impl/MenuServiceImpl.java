package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.model.dto.request.MenuRequest;
import com.st_carollus.ticket_system.model.dto.response.MenuResponse;
import com.st_carollus.ticket_system.model.entity.Menu;
import com.st_carollus.ticket_system.repository.MenuRepository;
import com.st_carollus.ticket_system.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public MenuResponse create(MenuRequest request) {
        Menu menu = Menu.builder()
                .menuName(request.getMenuName())
                .menuCode(request.getMenuCode())
                .menuUrl(request.getMenuUrl())
                .icon(request.getIcon())
                .orderIndex(request.getOrderIndex())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();
        return toResponse(menuRepository.save(menu));
    }

    @Override
    public List<MenuResponse> getAll() {
        return menuRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public MenuResponse getById(String id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public Menu getEntityByMenuCode(String menuCode) {
        return findEntityByMenuCode(menuCode);
    }

    private Menu findEntityByMenuCode(String menuCode) {
        return menuRepository.findByMenuCode(menuCode)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    @Override
    public MenuResponse update(String id, MenuRequest request) {
        Menu menu = findEntityById(id);
        menu.setMenuName(request.getMenuName());
        menu.setMenuCode(request.getMenuCode());
        menu.setMenuUrl(request.getMenuUrl());
        menu.setIcon(request.getIcon());
        menu.setOrderIndex(request.getOrderIndex());
        menu.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);

        return toResponse(menuRepository.save(menu));
    }

    @Override
    public void delete(String id) {
        Menu menu = findEntityById(id);
        menuRepository.delete(menu);
    }

    private Menu findEntityById(String id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    private MenuResponse toResponse(Menu menu) {
        return MenuResponse.builder()
                .menuName(menu.getMenuName())
                .menuCode(menu.getMenuCode())
                .menuUrl(menu.getMenuUrl())
                .icon(menu.getIcon())
                .orderIndex(menu.getOrderIndex())
                .isActive(menu.getIsActive())
                .build();
    }
}
