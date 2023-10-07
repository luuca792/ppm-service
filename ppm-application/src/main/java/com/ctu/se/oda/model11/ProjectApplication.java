package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.interfaces.IProjectApplication;
import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProjectApplication implements IProjectApplication {
    @Autowired
    private IProjectService projectService;
    @Override
    public CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest) {
        return this.projectService.createProject(createProjectCommandRequest);
    }
}