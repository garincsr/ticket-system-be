package com.st_carollus.ticket_system.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

// pageable null di-skip dari JSON (lewat @JsonInclude) supaya endpoint
// non-list (create/getById/update) responsenya tetap bersih tanpa "pageable": null

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;
    private T data;
    private PageMeta pageable;

    public static <T> ApiResponse<T> of(int status, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<List<T>> of(int status, Page<T> page) {
        return ApiResponse.<List<T>>builder()
                .status(status)
                .data(page.getContent())
                .pageable(PageMeta.from(page))
                .build();
    }
}
