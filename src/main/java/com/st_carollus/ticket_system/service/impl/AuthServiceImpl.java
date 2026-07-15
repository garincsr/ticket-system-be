package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.exception.InvalidCredentialsException;
import com.st_carollus.ticket_system.model.dto.request.LoginRequest;
import com.st_carollus.ticket_system.model.dto.response.LoginResponse;
import com.st_carollus.ticket_system.model.dto.response.RoleMenuAccessGroupedResponse;
import com.st_carollus.ticket_system.model.entity.User;
import com.st_carollus.ticket_system.repository.UserRepository;
import com.st_carollus.ticket_system.service.AuthService;
import com.st_carollus.ticket_system.service.RoleMenuAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleMenuAccessService roleMenuAccessService;

    @Override
    public LoginResponse login(LoginRequest request) {
        // Pesan error sengaja dibuat generik — bukan "username tidak
        // ditemukan" vs "password salah" secara terpisah — supaya orang
        // luar nggak bisa nebak username mana yang valid (user enumeration)
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Username atau password salah"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Username atau password salah");
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new InvalidCredentialsException("Akun tidak aktif, hubungi admin");
        }

        RoleMenuAccessGroupedResponse roleMenuAccess =
                roleMenuAccessService.getByRoleCode(user.getRole().getRoleCode());

        return LoginResponse.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roleMenuAccess(roleMenuAccess)
                .build();
    }
}
