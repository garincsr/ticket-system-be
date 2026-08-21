package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.model.entity.User;
import com.st_carollus.ticket_system.repository.UserRepository;
import com.st_carollus.ticket_system.security.UserPrincipal;
import com.st_carollus.ticket_system.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new UserPrincipal(user);
    }
}
