package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "start_at")
    private LocalDate startAt;
    @Column(name = "end_at")
    private LocalDate endAt;
    @Column(name = "duration")
    private Double duration;
    @Column(name = "creator_id")
    private UUID creatorId;

    public Project(String name, LocalDate startAt, LocalDate endAt, Double duration, UUID creatorId) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
        this.creatorId = creatorId;
    }

    public Project(UUID id, String name, LocalDate startAt, LocalDate endAt, Double duration, UUID creatorId) {
        this.id = id;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
        this.creatorId = creatorId;
    }
}
