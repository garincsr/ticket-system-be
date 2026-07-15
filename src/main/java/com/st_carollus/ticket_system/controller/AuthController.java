package com.st_carollus.ticket_system.controller;

import com.st_carollus.ticket_system.constant.APIUrl;
import com.st_carollus.ticket_system.model.dto.request.LoginRequest;
import com.st_carollus.ticket_system.model.dto.response.ApiResponse;
import com.st_carollus.ticket_system.model.dto.response.LoginResponse;
import com.st_carollus.ticket_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), response));
    }
}
