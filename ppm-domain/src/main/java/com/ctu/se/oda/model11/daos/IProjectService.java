package com.ctu.se.oda.model11.daos;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;

import jakarta.validation.Valid;

public interface IProjectService {
    void createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest);
    void updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest);
    List<RetrieveProjectQueryResponse> getAllProjects(UUID userId, Boolean isTemplate);
    RetrieveProjectQueryResponse getProjectById(UUID projectId);
    void deleteProject(UUID projectId);
    void cloneProject(UUID projectId, UUID userId);
}
