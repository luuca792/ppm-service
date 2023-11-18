package com.ctu.se.oda.model11.errors.responses;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class ErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String detail;

    public ErrorDetails(LocalDateTime localDateTime, String message, String detail) {
        this.localDateTime = localDateTime;
        this.message = message;
        this.detail = detail;
    }
}
