package com.st_carollus.ticket_system.repository;

import com.st_carollus.ticket_system.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByTicket_Id(String ticketId);
    List<Task> findByAssignedTo_Id(String userId);
}
