package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.helpers.TaskDateCalculationHelper;
import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class ProjectApplication implements IProjectApplication {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private TaskDateCalculationHelper taskDateCalculationHelper;

    @Override
    public CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest) {
        return projectService.createProject(createProjectCommandRequest);
    }
    @Override
    public UpdateProjectCommandResponse updateProject(UpdateProjectCommandRequest updateProjectCommandRequest) {
        return projectService.updateProject(updateProjectCommandRequest);
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

    @Override
    public void scheduleProject(UUID projectId) {
        taskDateCalculationHelper.dateScheduler(projectId);
    }
}