package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private ProjectStatus status = ProjectStatus.PENDING;

    @Column(name = "creator_id")
    private UUID creatorId;
    
}
