package com.ctu.se.oda.model11.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Table(name = "material_types")
@Data
@AllArgsConstructor
public class MaterialType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
 
    @Column(name = "name", nullable = false)
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "materialType")
    private List<Material> materials;
    
    public MaterialType() {}
}
