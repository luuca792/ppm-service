package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMaterialTypeRepository extends JpaRepository<MaterialType, UUID>{
}
