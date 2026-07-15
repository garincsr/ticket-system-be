package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleCode(String roleCode);
    Page<Role> findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(
            String roleName, String roleCode, Pageable pageable);
}
