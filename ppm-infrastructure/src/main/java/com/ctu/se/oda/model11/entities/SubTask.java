package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "subtasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    @Id
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = null;

    @Column(name = "task_parent_id")
    private UUID taskParentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;
}
