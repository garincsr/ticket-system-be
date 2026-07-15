package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, String> {
}
