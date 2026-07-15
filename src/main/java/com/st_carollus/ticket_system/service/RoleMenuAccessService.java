package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.RoleMenuAccessRequest;
import com.st_carollus.ticket_system.model.dto.response.RoleMenuAccessResponse;
import com.st_carollus.ticket_system.model.entity.RoleMenuAccess;

import java.util.List;

public interface RoleMenuAccessService {
    RoleMenuAccessResponse create(RoleMenuAccessRequest request);
    List<RoleMenuAccess> getEntityByRoleCode(String roleCode);
    RoleMenuAccessResponse update(String id, RoleMenuAccessRequest request);
    void delete(String id);
}
