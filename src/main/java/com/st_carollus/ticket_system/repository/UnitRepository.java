package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, String> {
}
