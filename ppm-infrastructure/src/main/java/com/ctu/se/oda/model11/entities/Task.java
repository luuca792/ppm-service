package com.ctu.se.oda.model11.entities;

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
    @Column(name="task_status")
    private TaskStatus taskStatus = TaskStatus.OPEN;

    public Task(String name, String description, LocalDate startAt, LocalDate endAt, TaskStatus taskStatus) {
        name = name;
        description = description;
        startAt = startAt;
        endAt = endAt;
        taskStatus = TaskStatus.OPEN;
    }

    public Task(UUID id, String name, String description, LocalDate startAt, LocalDate endAt, TaskStatus taskStatus) {
        id = id;
        name = name;
        description = description;
        startAt = startAt;
        endAt = endAt;
        taskStatus = taskStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        taskStatus = taskStatus;
    }
}
