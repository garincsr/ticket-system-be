package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.RoleMenuAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuAccessRepository extends JpaRepository<RoleMenuAccess, String> {
    List<RoleMenuAccess> findByRole_Id(String roleId);
}
