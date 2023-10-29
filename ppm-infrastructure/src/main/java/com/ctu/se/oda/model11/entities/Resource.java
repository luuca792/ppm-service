package com.ctu.se.oda.model11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
public class Resource implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "resource")
    private Task task;

    @JsonIgnore
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ResourceMaterial> resourceMaterials = new ArrayList<>();

    public Resource(UUID id, Task task) {
        this.id = id;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", task=" + task.getId() +
                '}';
    }
}
