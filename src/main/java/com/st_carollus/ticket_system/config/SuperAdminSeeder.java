package com.st_carollus.ticket_system.config;

import com.st_carollus.ticket_system.model.entity.Role;
import com.st_carollus.ticket_system.model.entity.Unit;
import com.st_carollus.ticket_system.model.entity.User;
import com.st_carollus.ticket_system.repository.RoleRepository;
import com.st_carollus.ticket_system.repository.UnitRepository;
import com.st_carollus.ticket_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuperAdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UnitRepository unitRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed.super-admin.username:superadmin}")
    private String seedUsername;

    @Value("${app.seed.super-admin.password:}")
    private String seedPassword;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername(seedUsername).isPresent()) {
            return;
        }

        if (seedPassword.isBlank()) {
            log.warn("SUPER_ADMIN seed password not set (app.seed.super-admin.password) — skipping seeding.");
            return;
        }

        Role superAdminRole = roleRepository.findByRoleCode("SUPER_ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .roleName("Super Admin")
                        .roleCode("SUPER_ADMIN")
                        .isActive(true)
                        .build()));

        Unit itUnit = unitRepository.findByUnitName("IT")
                .orElseGet(() -> unitRepository.save(Unit.builder().unitName("IT").build()));

        User superAdmin = User.builder()
                .username(seedUsername)
                .email("superadmin@stcarolus.local")
                .fullName("Super Administrator")
                .passwordHash(passwordEncoder.encode(seedPassword))
                .isActive(true)
                .isVerified(true)
                .unit(itUnit)
                .role(superAdminRole)
                .build();

        userRepository.save(superAdmin);
    }
}
