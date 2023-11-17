package com.ctu.se.oda.model11.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "resource_material")
@Data
@NoArgsConstructor
public class ResourceMaterial implements IEntity{
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
