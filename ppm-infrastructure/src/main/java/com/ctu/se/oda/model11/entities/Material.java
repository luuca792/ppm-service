package com.ctu.se.oda.model11.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
public class Material implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "material_type", nullable = false)
    private Long type;

    @JsonIgnore
    @OneToMany(mappedBy = "material")
    List<ResourceMaterial> resourceMaterials = new ArrayList<>();

    public Material(String name, Long type) {
        this.name = name;
        this.type = type;

    }
    public Material(UUID id, String name, Long type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
