package com.ctu.se.oda.model11;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctu.se.oda.model11.daos.IProjectService;
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
    @Override
    public void createProject(CreateProjectCommandRequest createProjectCommandRequest) {
        projectService.createProject(createProjectCommandRequest);
    }
    @Override
    public void updateProject(UpdateProjectCommandRequest updateProjectCommandRequest) {
        projectService.updateProject(updateProjectCommandRequest);
    }
    @Override
    public List<RetrieveProjectQueryResponse> listProject() {
        return projectService.listProject();
    }

    @Override
    public RetrieveProjectQueryResponse detailProject(UUID projectId) {
        return projectService.detailProject(projectId);
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectService.deleteProject(projectId);
    }

}