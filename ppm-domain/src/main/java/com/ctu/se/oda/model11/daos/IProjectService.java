package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;

public interface IProjectService {
    CreateProjectCommandResponse createProject(CreateProjectCommandRequest createProjectCommandRequest);
}
