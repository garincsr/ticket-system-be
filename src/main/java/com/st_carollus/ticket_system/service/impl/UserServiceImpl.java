package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.model.dto.request.UserRequest;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import com.st_carollus.ticket_system.model.entity.Role;
import com.st_carollus.ticket_system.model.entity.User;
import com.st_carollus.ticket_system.repository.UserRepository;
import com.st_carollus.ticket_system.service.RoleService;
import com.st_carollus.ticket_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        Role role = roleService.getEntityByRoleCode(request.getRoleCode());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .role(role)
                .build();

        return toResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponse> getAll(String search, String sortBy, String direction, int page, int size) {
        String safeSortField = (sortBy != null && USER_SORTABLE_FIELDS.contains(sortBy))
                ? sortBy
                : "fullName";

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, safeSortField));

        Page<User> result = (search == null || search.isBlank())
                ? userRepository.findAll(pageable)
                : userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrFullNameContainingIgnoreCase(
                        search, search, search, pageable);

        return result.map(this::toResponse);
    }

    @Override
    public UserResponse getById(String id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        User user = findEntityById(id);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }

        Role role = roleService.getEntityByRoleCode(request.getRoleCode());
        user.setRole(role);

        return toResponse(userRepository.save(user));
    }

    @Override
    public void delete(String id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    private User findEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private static final Set<String> USER_SORTABLE_FIELDS = Set.of("fullName", "username");

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .isActive(user.getIsActive())
                .role(user.getRole())
                .build();
    }
}
