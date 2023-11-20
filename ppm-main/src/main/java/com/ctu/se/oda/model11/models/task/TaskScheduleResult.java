package com.ctu.se.oda.model11.models.task;

import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Builder
public class TaskScheduleResult {
    private String taskName;
    private Double taskDuration;
    private LocalDate taskStartDate;
    private LocalDate taskEndDate;
}
