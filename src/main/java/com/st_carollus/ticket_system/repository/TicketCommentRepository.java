package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketCommentRepository extends JpaRepository<TicketComment, String> {
    List<TicketComment> findByTicket_IdOrderByCreatedAtAsc(String ticketId);
}
