package com.ctu.se.oda.model11;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.helpers.TaskDateCalculationHelper;
import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ProjectApplication implements IProjectApplication {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private TaskDateCalculationHelper taskDateCalculationHelper;

	@Override
	public void createProject(CreateProjectCommandRequest createProjectCommandRequest) {
		projectService.createProject(createProjectCommandRequest);
	}

	@Override
	public void updateProject(UpdateProjectCommandRequest updateProjectCommandRequest) {

		projectService.updateProject(updateProjectCommandRequest);
	}

	@Override
	public List<RetrieveProjectQueryResponse> getAllProjects(String userId, Boolean isTemplate) {
		return projectService.getAllProjects(Optional.ofNullable(userId).map(UUID::fromString).orElse(null), isTemplate);
	}

	@Override
	public RetrieveProjectQueryResponse getProjectById(UUID projectId) {
		return projectService.getProjectById(projectId);
	}

	@Override
	public void deleteProject(UUID projectId) {
		projectService.deleteProject(projectId);
	}

	@Override
	public void scheduleProject(UUID projectId) {
		taskDateCalculationHelper.dateScheduler(projectId);
	}

	@Override
	public void cloneProject(String projectId, String userId) {
		projectService.cloneProject(UUID.fromString(projectId), UUID.fromString(userId));
	}
}
