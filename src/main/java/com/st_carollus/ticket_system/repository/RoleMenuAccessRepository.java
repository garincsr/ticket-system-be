package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.RoleMenuAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuAccessRepository extends JpaRepository<RoleMenuAccess, String> {
    // Used to build the sidebar: all menus (with CRUD flags) visible to a given role
    List<RoleMenuAccess> findByRole_Id(String roleId);
}
