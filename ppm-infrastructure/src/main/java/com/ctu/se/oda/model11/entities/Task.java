package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "tasks")
@NoArgsConstructor
public class Task implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "start_at")
    private LocalDate startAt;

    @Column(name = "end_at")
    private LocalDate endAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private TaskStatus status = TaskStatus.OPEN;

    public Task(String name, String description, LocalDate startAt, LocalDate endAt, TaskStatus taskStatus) {
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = TaskStatus.OPEN;
    }

    public Task(UUID id, String name, String description, LocalDate startAt, LocalDate endAt, TaskStatus taskStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = taskStatus;
    }
}
