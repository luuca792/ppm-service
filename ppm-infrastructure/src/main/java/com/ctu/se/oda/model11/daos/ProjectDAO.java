package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.entities.Task;
import com.ctu.se.oda.model11.entities.TaskDependency;
import com.ctu.se.oda.model11.enums.DependencyType;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.repositories.ITaskDependencyRepository;
import com.ctu.se.oda.model11.repositories.ITaskRepository;
import com.ctu.se.oda.model11.utils.ModelMapperUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class ProjectDAO implements IProjectService {
	
	@Autowired
	private IProjectRepository projectRepository;

	@Autowired
	private ITaskRepository taskRepository;

	@Autowired
	private ITaskDependencyRepository taskDependencyRepository;

	@Autowired
	private IInfrastructureMapper<CreateProjectCommandRequest, Project> createProjectEntityMapper;

	@Autowired
	private IInfrastructureMapper<UpdateProjectCommandRequest, Project> updateProjectEntityMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest) {
		if (Objects.nonNull(createProjectCommandRequest.getProjectStartAt()) && Objects.nonNull(createProjectCommandRequest.getProjectEndAt())) {
			if (createProjectCommandRequest.getProjectStartAt().isAfter(createProjectCommandRequest.getProjectEndAt())) {
				throw new IllegalArgumentException(CustomErrorMessage.START_DATE_AFTER_END_DATE);
			}
		}
		projectRepository.save(createProjectEntityMapper.convert(createProjectCommandRequest));
	}

	@Override
	public void updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest) {
		var retrieveProject = projectRepository.findById(updateProjectCommandRequest.getProjectId());
		if (retrieveProject.isEmpty()) {
			throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_NOT_FOUND);
		}

		if (Objects.nonNull(updateProjectCommandRequest.getProjectStartAt()) && Objects.nonNull(updateProjectCommandRequest.getProjectEndAt())) {
			if (updateProjectCommandRequest.getProjectStartAt() .isAfter(updateProjectCommandRequest.getProjectEndAt())) {
				throw new IllegalArgumentException(CustomErrorMessage.START_DATE_AFTER_END_DATE);
			}
		}

		Project updatedProject = updateProjectEntityMapper.convert(updateProjectCommandRequest);
		ModelMapperUtil.copy(updatedProject, retrieveProject.get());
		projectRepository.save(retrieveProject.get());
	}

	@Override
	public List<RetrieveProjectQueryResponse> getAllProjects(UUID userId, Boolean isTemplate) {
		List<Project> projects;
		if (Objects.nonNull(userId)) {
			projects = projectRepository.findAllByUserId(userId);
		} else {
			projects = projectRepository.findAll();
		}
		return projects.stream()
				.filter(project -> Objects.isNull(isTemplate) || Objects.equals(project.getIsTemplate(), isTemplate))
				.map(project -> RetrieveProjectQueryResponse.builder()
				.projectId(project.getId())
				.projectName(project.getName()).projectStartAt(project.getStartAt())
				.projectEndAt(project.getEndAt()).projectDuration(project.getDuration())
				.projectStatus(project.getStatus()).projectCreatorId(project.getUserId())
				.isTemplate(project.getIsTemplate())
				.projectDuration(project.getDuration())
				.build())
				.collect(Collectors.toList());
	}

	@Override
	public RetrieveProjectQueryResponse getProjectById(UUID projectId) {
		var retrievedProjectOptional = projectRepository.findById(projectId);
		if (retrievedProjectOptional.isEmpty()) {
			throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_NOT_FOUND);
		}
		var retrievedProject = retrievedProjectOptional.get();
		return RetrieveProjectQueryResponse.builder()
				.projectId(retrievedProject.getId())
				.projectName(retrievedProject.getName()).projectStartAt(retrievedProject.getStartAt())
				.projectEndAt(retrievedProject.getEndAt()).projectDuration(retrievedProject.getDuration())
				.projectStatus(retrievedProject.getStatus()).projectCreatorId(retrievedProject.getUserId())
				.build();
	}

	@Override
	public void deleteProject(UUID projectId) {
		Optional<Project> retrievedProject = projectRepository.findById(projectId);
		if (retrievedProject.isEmpty()) {
			throw new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_NOT_FOUND);
		}
		projectRepository.deleteById(projectId);
	}

	@Override
	public void cloneProject(UUID projectId, UUID userId) {
		Project retrievedProject = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException(CustomErrorMessage.PROJECT_ID_NOT_FOUND));
		Project clonedProject = Project.builder()
				.name(retrievedProject.getName())
				.startAt(retrievedProject.getStartAt())
				.endAt(retrievedProject.getEndAt())
				.duration(retrievedProject.getDuration())
				.status(retrievedProject.getStatus())
				.isTemplate(false)
				.userId(userId)
				.build();

		projectRepository.save(clonedProject);

		List<Task> originalTasks = retrievedProject.getTasks();
		List<Task> clonedTasks = originalTasks.stream()
				.map(originalTask -> {
					Task clonedTask = new Task();
					BeanUtils.copyProperties(originalTask, clonedTask);
					clonedTask.setId(null);
					clonedTask.setProjectId(clonedProject);
					return clonedTask;
				})
				.collect(Collectors.toList());

		clonedProject.setTasks(clonedTasks);
		projectRepository.save(clonedProject);
		cloneTaskDependencies(retrievedProject, clonedProject);
	}

	private void cloneTaskDependencies(Project originalProject, Project clonedProject) {
		List<TaskDependency> originalTaskDependencies = originalProject.getTasks().stream()
				.flatMap(task -> taskDependencyRepository.findAllByTaskId(task).stream())
				.collect(Collectors.toList());

		List<TaskDependency> clonedTaskDependencies = originalTaskDependencies.stream()
				.map(originalDependency -> {
					TaskDependency clonedDependency = new TaskDependency();
					BeanUtils.copyProperties(originalDependency, clonedDependency);
					clonedDependency.setId(null);  // Set ID to null for database to generate a new one

					// Update the task and dependentTask references to point to the cloned tasks
					Task clonedTask = findClonedTask(originalDependency.getTaskId(), clonedProject.getTasks());
					Task clonedDependentTask = findClonedTask(originalDependency.getDependentTaskId(), clonedProject.getTasks());

					clonedDependency.setTaskId(clonedTask);
					clonedDependency.setDependentTaskId(clonedDependentTask);

					return clonedDependency;
				})
				.collect(Collectors.toList());

		taskDependencyRepository.saveAll(clonedTaskDependencies);
	}

	private Task findClonedTask(Task originalTask, List<Task> clonedTasks) {
		// Find the corresponding cloned task based on some criteria (e.g., task name, ID, etc.)
		// This depends on your specific logic for identifying corresponding tasks.
		return clonedTasks.stream()
				.filter(clonedTask -> clonedTask.getName().equals(originalTask.getName()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Corresponding cloned task not found"));
	}
}
