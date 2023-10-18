package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, UUID> {
}
