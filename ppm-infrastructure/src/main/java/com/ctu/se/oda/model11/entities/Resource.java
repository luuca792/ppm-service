package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
public class Resource implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "task_id", nullable = false)
    private UUID taskId;

    public Resource(String name, UUID taskId) {
        this.name = name;
        this.taskId = taskId;

    }

    public Resource(Long id, String name, UUID taskId) {
        this.id = id;
        this.name = name;
        this.taskId = taskId;
    }
}
