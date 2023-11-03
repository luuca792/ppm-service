package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface IProjectApplication {
    CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest);

    UpdateProjectCommandResponse updateProject(UpdateProjectCommandRequest updateProjectCommandRequest);

    List<RetrieveProjectQueryResponse> listProject();

    RetrieveProjectQueryResponse detailProject(UUID projectId);

    void addEmailToProject(UUID projectId, UUID emailId, boolean status);

    void deleteProject(UUID projectId);
}
