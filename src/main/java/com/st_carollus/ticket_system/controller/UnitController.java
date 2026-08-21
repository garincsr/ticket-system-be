package com.st_carollus.ticket_system.controller;

import com.st_carollus.ticket_system.constant.APIUrl;
import com.st_carollus.ticket_system.model.dto.response.ApiResponse;
import com.st_carollus.ticket_system.model.dto.response.UnitResponse;
import com.st_carollus.ticket_system.service.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIUrl.UNIT_API)
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<ApiResponse<UnitResponse>> create(@Valid @RequestBody String unitName) {
        UnitResponse created = unitService.create(unitName);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.of(HttpStatus.CREATED.value(), created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UnitResponse>>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UnitResponse> result = unitService.getAll(search, sortBy, direction, page, size);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UnitResponse>> getById(@PathVariable String id) {
        UnitResponse result = unitService.getById(id);

        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UnitResponse>> update(@PathVariable String id, @Valid @RequestBody String unitName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.of(HttpStatus.OK.value(), unitService.update(id, unitName)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
