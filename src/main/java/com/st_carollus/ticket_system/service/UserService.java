package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.UserAdminUpdateRequest;
import com.st_carollus.ticket_system.model.dto.request.UserCreateRequest;
import com.st_carollus.ticket_system.model.dto.request.UserRequest;
import com.st_carollus.ticket_system.model.dto.request.UserSelfUpdateRequest;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponse create(UserCreateRequest request);
    Page<UserResponse> getAll(String search, String sortBy, String direction, int page, int size);
    UserResponse getById(String id);
    UserResponse updateByAdmin(String id, UserAdminUpdateRequest request);
    UserResponse updateSelf(String userId, UserSelfUpdateRequest request);
    void delete(String id);
}
