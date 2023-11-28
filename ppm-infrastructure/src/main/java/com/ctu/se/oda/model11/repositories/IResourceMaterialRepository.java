package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.Material;
import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.entities.ResourceMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IResourceMaterialRepository extends JpaRepository<ResourceMaterial, UUID> {
    @Query("SELECT e FROM ResourceMaterial e WHERE e.material = :material AND e.resource = :resource")
    List<ResourceMaterial> findByMaterialAndResource(@Param("material") Material material, @Param("resource") Resource resource);
}
