package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
    public List<Project> findAllByUserId(UUID userId);
}
