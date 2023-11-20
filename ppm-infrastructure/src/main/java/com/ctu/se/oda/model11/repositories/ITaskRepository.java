package com.ctu.se.oda.model11.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByProjectId(Project project);
}
