package com.ctu.se.oda.model11.interfaces;

import java.util.List;
import java.util.UUID;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;

public interface IProjectApplication {
    void createProject(CreateProjectCommandRequest createProjectCommandRequest);

    void updateProject(UpdateProjectCommandRequest updateProjectCommandRequest);

    List<RetrieveProjectQueryResponse> listProject();

    RetrieveProjectQueryResponse detailProject(UUID projectId);

    void deleteProject(UUID projectId);

    void scheduleProject(UUID project);
}
