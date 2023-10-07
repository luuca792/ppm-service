package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;

public interface IProjectApplication {
    CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest);
}
