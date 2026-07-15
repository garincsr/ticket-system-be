package com.st_carollus.ticket_system.service;

import com.st_carollus.ticket_system.model.dto.request.LoginRequest;
import com.st_carollus.ticket_system.model.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
