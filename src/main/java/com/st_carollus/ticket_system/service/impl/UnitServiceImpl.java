package com.st_carollus.ticket_system.service.impl;

import com.st_carollus.ticket_system.exception.ResourceNotFoundException;
import com.st_carollus.ticket_system.model.dto.response.UnitResponse;
import com.st_carollus.ticket_system.model.entity.Unit;
import com.st_carollus.ticket_system.repository.UnitRepository;
import com.st_carollus.ticket_system.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    @Override
    @Transactional
    public UnitResponse create(String unitName) {
       Unit unit = Unit.builder()
                .unitName(unitName)
                .build();

       return toResponse(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnitResponse> getAll(String search, String sortBy, String direction, int page, int size) {
        String safeSortField = sortBy == null ? "unitName" : sortBy;
        Sort.Direction safeSortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(safeSortDirection, safeSortField));

        Page<Unit> result = (search == null || search.isBlank())
                ? unitRepository.findAll(pageable)
                : unitRepository.findByUnitNameContainingIgnoreCase(search, pageable);

        return result.map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UnitResponse getById(String id) {

        return toResponse(findEntityById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Unit getEntityByUnitName(String unitName) {
        return findEntityByUnitName(unitName);
    }

    @Override
    public UnitResponse update(String id, String unitName) {
        Unit unit = findEntityById(id);
        unit.setUnitName(unitName);

        return toResponse(unitRepository.save(unit));
    }

    @Override
    public void delete(String id) {
        Unit unit = findEntityById(id);

        unitRepository.delete(unit);
    }

    private Unit findEntityByUnitName(String unitName) {

        return unitRepository.findByUnitName(unitName)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
    }

    private Unit findEntityById(String id) {
        return  unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
    }

    private UnitResponse toResponse(Unit unit) {
        return UnitResponse.builder()
                .unitName(unit.getUnitName())
                .build();
    }
}
