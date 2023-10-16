package com.ctu.se.oda.model11.models.commands.requests.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateTaskCommandRequest {
    @NotBlank
    @Size(max = 250)
    private String taskName;
    @NotBlank
    @Size(max = 500)
    private String taskDescription;
    @NotNull
    private LocalDate taskStartAt;
    @NotNull
    private LocalDate taskEndAt;

    public CreateTaskCommandRequest(String taskName, String taskDescription, LocalDate taskStartAt, LocalDate taskEndAt) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartAt = taskStartAt;
        this.taskEndAt = taskEndAt;
    }

    @Override
    public String toString() {
        return "CreateTaskCommandRequest{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStartAt=" + taskStartAt +
                ", taskEndAt=" + taskEndAt +
                '}';
    }
}
