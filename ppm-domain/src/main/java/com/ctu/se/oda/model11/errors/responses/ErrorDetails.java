package com.ctu.se.oda.model11.errors.responses;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Data
public class ErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String detail;
}
