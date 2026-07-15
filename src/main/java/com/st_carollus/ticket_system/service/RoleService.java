package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.RoleRequest;
import com.st_carollus.ticket_system.model.dto.response.RoleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    Page<RoleResponse> getAll(String search, Pageable pageable);
    RoleResponse getById(String id);
    RoleResponse getByRoleCode(String roleCode);
    RoleResponse update(String id, RoleRequest request);
    void delete(String id);
}
