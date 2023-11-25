package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.SubTask;
import com.ctu.se.oda.model11.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISubTaskRepository extends JpaRepository<SubTask, UUID> {
    public List<SubTask> findAllByTaskParentId(Task taskParentId);
}
