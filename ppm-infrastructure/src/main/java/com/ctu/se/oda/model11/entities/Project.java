package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.entities.ProjectStatus;
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private ProjectStatus status = ProjectStatus.PENDING;

    public Project(String name, Double duration, UUID creatorId, ProjectStatus status) {
        name = name;
        duration = duration;
        creatorId = creatorId;
        status = status.PENDING;
    }

    public Project(UUID id, String name, Double duration, UUID creatorId, ProjectStatus status) {
        id = id;
        name = name;
        duration = duration;
        creatorId = creatorId;
        status = status;
    }
    public ProjectStatus getProjectStatus() {
        return status;
    }

    public void setProjectStatus(ProjectStatus status) {
        status = status;
    }
}
