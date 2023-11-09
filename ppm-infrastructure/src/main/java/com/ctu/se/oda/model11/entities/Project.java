package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.ProjectStatus;
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private ProjectStatus status = ProjectStatus.PENDING;
    @Column(name = "creator_id")
    private UUID creatorId;

    public Project(String name, Double duration, ProjectStatus status, UUID creatorId) {
        this.name = name;
        this.duration = duration;
        this.status = ProjectStatus.PENDING;
        this.creatorId = creatorId;
    }

    public Project(UUID id, String name, Double duration, ProjectStatus status, UUID creatorId) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.status = status;
        this.creatorId = creatorId;
    }
}
