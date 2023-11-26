package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Project;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.repositories.IProjectRepository;
import com.ctu.se.oda.model11.utils.ModelMapperUtil;
import jakarta.validation.Valid;
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
	private IInfrastructureMapper<CreateProjectCommandRequest, Project> createProjectEntityMapper;

	@Autowired
	private IInfrastructureMapper<UpdateProjectCommandRequest, Project> updateProjectEntityMapper;

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
}
