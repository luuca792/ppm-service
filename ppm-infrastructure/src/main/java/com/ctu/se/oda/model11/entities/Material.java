package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
public class Material implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "material_type", nullable = false)
    private Long type;

    public Material(String name, Long type) {
        this.name = name;
        this.type = type;

    }

    public Material(Long id, String name, Long type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
