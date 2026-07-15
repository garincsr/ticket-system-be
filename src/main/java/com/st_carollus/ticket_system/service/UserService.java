package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.UserRequest;
import com.st_carollus.ticket_system.model.dto.response.RoleResponse;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse create(UserRequest request);
    Page<RoleResponse> getAll(String search, Pageable pageable);
    UserResponse getById(String id);
    UserResponse update(String id, UserRequest request);
    void delete(String id);
}
