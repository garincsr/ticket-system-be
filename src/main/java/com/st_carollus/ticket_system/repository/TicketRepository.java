package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// JpaSpecificationExecutor lets findAll(spec, pageable) combine dynamic
// filters (see repository/specification/TicketSpecification) with pagination
public interface TicketRepository extends JpaRepository<Ticket, String>, JpaSpecificationExecutor<Ticket> {
}
