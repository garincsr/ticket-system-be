package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.model.dto.request.UserRequest;
import com.st_carollus.ticket_system.model.dto.response.RoleResponse;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import com.st_carollus.ticket_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse create(UserRequest request) {
        return null;
    }

    @Override
    public Page<RoleResponse> getAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public UserResponse getById(String id) {
        return null;
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
