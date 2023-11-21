package com.ctu.se.oda.model11.entities;

import java.util.UUID;

import com.ctu.se.oda.model11.enums.DependencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "task_dependency")
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskDependency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "task_id")
    @NonNull
    private UUID taskId;

    @Column(name = "dependent_task_id")
    @NonNull
    private UUID dependentTaskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dependency_type")
    @NonNull
    private DependencyType dependencyType;
}
