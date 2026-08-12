package com.st_carollus.ticket_system.controller;

import com.st_carollus.ticket_system.constant.APIUrl;
import com.st_carollus.ticket_system.model.dto.request.UserRequest;
import com.st_carollus.ticket_system.model.dto.response.ApiResponse;
import com.st_carollus.ticket_system.model.dto.response.UserResponse;
import com.st_carollus.ticket_system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request) {
        UserResponse created = userService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.of(HttpStatus.CREATED.value(), created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserResponse> result = userService.getAll(search, sortBy, direction, page, size);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable String id) {
        UserResponse result = userService.getById(id);

        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable String id, @Valid @RequestBody UserRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), userService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
