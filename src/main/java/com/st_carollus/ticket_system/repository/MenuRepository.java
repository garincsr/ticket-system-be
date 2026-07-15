package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {
}
