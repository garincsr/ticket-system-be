package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.exception.ResourceNotFoundException;
import com.st_carollus.ticket_system.model.dto.request.RoleRequest;
import com.st_carollus.ticket_system.model.dto.response.RoleResponse;
import com.st_carollus.ticket_system.model.entity.Role;
import com.st_carollus.ticket_system.repository.RoleRepository;
import com.st_carollus.ticket_system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public RoleResponse create(RoleRequest request) {
        Role role = Role.builder()
                .roleName(request.getRoleName())
                .roleCode(request.getRoleCode())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true )
                .build();
        return toResponse(roleRepository.save(role));
    }

    @Override
    public Page<RoleResponse> getAll(String search, Pageable pageable) {
        Page<Role> page = (search == null || search.isBlank())
                ? roleRepository.findAll(pageable)
                : roleRepository.findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(search, search, pageable);

        return page.map(this::toResponse);
    }

    @Override
    public RoleResponse getById(String id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public Role getEntityByRoleCode(String roleCode) {
        return findEntityByRoleCode(roleCode);
    }

    @Override
    public RoleResponse update(String id, RoleRequest request) {
        Role role = findEntityById(id);
        role.setRoleName(request.getRoleName());
        role.setRoleCode(request.getRoleCode());
        if (request.getIsActive() != null) {
            role.setIsActive(request.getIsActive());
        }
        return toResponse(roleRepository.save(role));
    }

    @Override
    public void delete(String id) {
        Role role = findEntityById(id);
        roleRepository.delete(role);
    }

    private Role findEntityById(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id: " + id + " not found"));
    }

    private Role findEntityByRoleCode(String roleCode) {
        return roleRepository.findByRoleCode(roleCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role code: " + roleCode + " not found"));
    }

    private RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .roleCode(role.getRoleCode())
                .isActive(role.getIsActive())
                .build();
    }
}
