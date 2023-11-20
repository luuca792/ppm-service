package com.ctu.se.oda.model11.models.commands.requests.project;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CreateProjectCommandRequest {
    @NotBlank
    @Size(max = 250)
    private String projectName;

    @Nullable
    private LocalDate projectStartAt;
    @Nullable
    private LocalDate projectEndAt;
    @NotNull
    @Positive
    private Double projectDuration;

    @NotNull
    private UUID projectCreatorId;

    public CreateProjectCommandRequest(String projectName, LocalDate projectStartAt, LocalDate projectEndAt, Double projectDuration, UUID projectCreatorId) {
        this.projectName = projectName;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.projectDuration = projectDuration;
        this.projectCreatorId = projectCreatorId;
    }

    @Override
    public String toString() {
        return "CreateProjectCommandRequest{" +
                "projectName=" + this.projectName +
                ", projectStartAt=" + this.projectStartAt +
                ", projectEndAt=" + this.projectEndAt +
                ", projectDuration=" + this.projectDuration +
                ", projectCreatorId=" + this.projectCreatorId +
                '}';
    }

}
