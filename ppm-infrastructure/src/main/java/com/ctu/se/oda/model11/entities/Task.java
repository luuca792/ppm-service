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
    @Column(name = "duration")
    private Double duration;

    public Task(String name, String description, LocalDate startAt, LocalDate endAt, Double duration) {
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
    }

    public Task(UUID id, String name, String description, LocalDate startAt, LocalDate endAt, Double duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
    }
}
