package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface IProjectService {
    CreateProjectCommandResponse createProject(@Valid CreateProjectCommandRequest createProjectCommandRequest);

    UpdateProjectCommandResponse updateProject(@Valid UpdateProjectCommandRequest updateProjectCommandRequest);

    List<RetrieveProjectQueryResponse> listProject();

    RetrieveProjectQueryResponse detailProject(UUID projectId);

    void deleteProject(UUID projectId);
}
