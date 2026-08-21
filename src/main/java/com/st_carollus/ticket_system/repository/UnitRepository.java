package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, String> {
    Page<Unit> findByUnitNameContainingIgnoreCase(String unitName, Pageable pageable);
    Optional<Unit> findByUnitName(String unitName);
}
