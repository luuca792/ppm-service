package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "duration")
    private Double duration;
    @Column(name = "creator_id")
    private UUID creatorId;

    public Project(String name, Double duration, UUID creatorId) {
        name = name;
        duration = duration;
        creatorId = creatorId;
    }

    public Project(UUID id, String name, Double duration, UUID creatorId) {
        id = id;
        name = name;
        duration = duration;
        creatorId = creatorId;
    }
}
