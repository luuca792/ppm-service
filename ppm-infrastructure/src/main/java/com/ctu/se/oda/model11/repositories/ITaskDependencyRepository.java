package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.entities.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskDependencyRepository extends JpaRepository<TaskDependency, UUID> {
	List<TaskDependency> findAllByTaskId(Task taskId);
}
