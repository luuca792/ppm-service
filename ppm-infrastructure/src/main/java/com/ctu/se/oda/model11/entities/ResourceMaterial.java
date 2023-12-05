package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "resource_material")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "amount")
    private Double amount;
    
    @ManyToOne
    private Material material;
    
    @ManyToOne
    private Resource resource;
}
