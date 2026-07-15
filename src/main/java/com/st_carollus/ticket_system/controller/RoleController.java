package com.st_carollus.ticket_system.controller;

import com.st_carollus.ticket_system.constant.APIUrl;
import com.st_carollus.ticket_system.model.dto.request.RoleRequest;
import com.st_carollus.ticket_system.model.dto.response.ApiResponse;
import com.st_carollus.ticket_system.model.dto.response.RoleResponse;
import com.st_carollus.ticket_system.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.ROLE_API)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody RoleRequest request) {
        RoleResponse created = roleService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.of(HttpStatus.CREATED.value(), created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        Page<RoleResponse> page = roleService.getAll(search, pageable);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), roleService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable String id, @Valid @RequestBody RoleRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), roleService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
