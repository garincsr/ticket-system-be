package com.st_carollus.ticket_system.security;

import com.st_carollus.ticket_system.model.entity.User;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private final String id;
    private final String username;
    private final String passwordHash;
    private final Boolean isActive;
    private final String roleCode;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.isActive = user.getIsActive();
        this.roleCode = user.getRole().getRoleCode();
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleCode));
    }

    @Override
    public @Nullable String getPassword() {
        return passwordHash;
    }

    @Override
    public @NonNull String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive != null && isActive;
    }
}
