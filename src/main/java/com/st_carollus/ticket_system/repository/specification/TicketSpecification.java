package com.st_carollus.ticket_system.repository.specification;

import com.st_carollus.ticket_system.constant.TicketPriority;
import com.st_carollus.ticket_system.constant.TicketStatus;
import com.st_carollus.ticket_system.model.entity.Task;
import com.st_carollus.ticket_system.model.entity.Ticket;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

// Each method returns a Specification<Ticket> (a WHERE clause fragment).
// Combine them with .and() in the service layer, then pass the combined
// spec + a Pageable into TicketRepository.findAll(spec, pageable).
public class TicketSpecification {

    public static Specification<Ticket> hasUnit(String unitId) {
        return (root, query, cb) -> unitId == null ? null
                : cb.equal(root.get("unit").get("id"), unitId);
    }

    public static Specification<Ticket> hasStatus(TicketStatus status) {
        return (root, query, cb) -> status == null ? null
                : cb.equal(root.get("status"), status);
    }

    public static Specification<Ticket> hasPriority(TicketPriority priority) {
        return (root, query, cb) -> priority == null ? null
                : cb.equal(root.get("priority"), priority);
    }

    // This is the IT_STAFF row-level filter: only tickets that have at
    // least one Task assigned to this user. Leave userId null (don't
    // apply this spec) when the caller is ADMIN, so they see everything.
    public static Specification<Ticket> assignedToUser(String userId) {
        return (root, query, cb) -> {
            if (userId == null) {
                return null;
            }
            query.distinct(true);
            Join<Ticket, Task> taskJoin = root.join("tasks");
            return cb.equal(taskJoin.get("assignedTo").get("id"), userId);
        };
    }
}
