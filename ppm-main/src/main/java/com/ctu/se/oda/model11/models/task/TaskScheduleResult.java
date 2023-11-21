package com.ctu.se.oda.model11.models.task;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskScheduleResult {
    private String taskName;
    private Double taskDuration;
    private LocalDate taskStartDate;
    private LocalDate taskEndDate;
}
