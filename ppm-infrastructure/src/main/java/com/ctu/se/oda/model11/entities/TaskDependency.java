package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.DependencyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
