package com.ctu.se.oda.model11.entities;

import com.ctu.se.oda.model11.enums.MaterialType;
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "material_type_id", nullable = false)
    private MaterialType materialTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "material")
    List<ResourceMaterial> resourceMaterials = new ArrayList<>();

    public Material(String name, MaterialType materialTypeName) {
        this.name = name;
        this.materialTypeName = materialTypeName;

    }
    public Material(UUID id, String name, MaterialType materialTypeName) {
        this.id = id;
        this.name = name;
        this.materialTypeName = materialTypeName;
    }
}
