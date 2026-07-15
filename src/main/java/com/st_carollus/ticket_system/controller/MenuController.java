package com.st_carollus.ticket_system.controller;

import com.st_carollus.ticket_system.constant.APIUrl;
import com.st_carollus.ticket_system.model.dto.request.MenuRequest;
import com.st_carollus.ticket_system.model.dto.response.ApiResponse;
import com.st_carollus.ticket_system.model.dto.response.MenuResponse;
import com.st_carollus.ticket_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.MENU_API)
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ApiResponse<MenuResponse>> create(@Valid @RequestBody MenuRequest request) {
        MenuResponse created = menuService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.of(HttpStatus.CREATED.value(), created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuResponse>>> getAll() {
        List<MenuResponse> list = menuService.getAll();
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponse>> getById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), menuService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponse>> update(@PathVariable String id, @Valid @RequestBody MenuRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), menuService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
