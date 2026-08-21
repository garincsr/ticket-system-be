package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.exception.InvalidCredentialsException;
import com.st_carollus.ticket_system.exception.ResourceNotFoundException;
import com.st_carollus.ticket_system.model.dto.request.UserAdminUpdateRequest;
import com.st_carollus.ticket_system.model.dto.request.UserCreateRequest;
import com.st_carollus.ticket_system.model.dto.request.UserSelfUpdateRequest;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import com.st_carollus.ticket_system.model.entity.Role;
import com.st_carollus.ticket_system.model.entity.Unit;
import com.st_carollus.ticket_system.model.entity.User;
import com.st_carollus.ticket_system.repository.UserRepository;
import com.st_carollus.ticket_system.service.RoleService;
import com.st_carollus.ticket_system.service.UnitService;
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
    private final UnitService unitService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest request) {
        Role role = roleService.getEntityByRoleCode(request.getRoleCode());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .role(role)
                .build();

        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public UserResponse getById(String id) {
        return toResponse(findEntityById(id));
    }

    @Override
    @Transactional
    public UserResponse updateByAdmin(String id, UserAdminUpdateRequest request) {
        User user = findEntityById(id);

        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPassword() != null && !request.getPassword().isBlank()) user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getIsActive() != null) user.setIsActive(request.getIsActive());
        if (request.getIsVerified() != null) user.setIsVerified(request.getIsVerified());
        if (request.getRoleCode() != null) {
            Role role = roleService.getEntityByRoleCode(request.getRoleCode());
            user.setRole(role);
        }

        if (request.getUnitName() != null) {
            Unit unit = unitService.getEntityByUnitName(request.getUnitName());
            user.setUnit(unit);
        };

        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateSelf(String userId, UserSelfUpdateRequest request) {
        User user = findEntityById(userId);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid password");
        }
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        }

        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(String id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    private User findEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private static final Set<String> USER_SORTABLE_FIELDS = Set.of("fullName", "username");

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .unitName(user.getUnit().getUnitName())
                .isActive(user.getIsActive())
                .isVerified(user.getIsVerified())
                .roleCode(user.getRole().getRoleCode())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
