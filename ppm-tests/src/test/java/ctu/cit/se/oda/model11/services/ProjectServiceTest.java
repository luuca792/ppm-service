package ctu.cit.se.oda.model11.services;

import com.ctu.se.oda.model11.Main;
import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.models.commands.requests.project.CreateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.project.CreateProjectCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.project.UpdateProjectCommandResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

@SpringBootTest(classes = Main.class)
@DirtiesContext
public class ProjectServiceTest {
    private static final String PROJECT_NAME = "project_001";
    private static final String PROJECT_NAME_CHANGED = "project_002";
    private static final Double PROJECT_DURATION = 100.0;
    private static final Double PROJECT_DURATION_CHANGED = 100.1;
    private static final UUID PROJECT_CREATOR_ID = UUID.fromString("9ce12f1d-46c3-4f60-a125-7bbaca76876f");
    private static final UUID PROJECT_CREATOR_ID_CHANGED = UUID.fromString("9ce12f1d-46c3-4f60-a125-7bbaca76876f");

    @Autowired
    private IProjectService projectService;
    @Test
    @DirtiesContext
    public void createProject_fullField() {
        var createdProject = this.projectService.createProject(CreateProjectCommandRequest.builder()
                .projectName(PROJECT_NAME)
                .projectDuration(PROJECT_DURATION)
                .projectCreatorId(PROJECT_CREATOR_ID)
                .build());
        Assertions.assertEquals(
                createdProject,
                CreateProjectCommandResponse.builder()
                        .projectName(PROJECT_NAME)
                        .projectDuration(PROJECT_DURATION)
                        .projectCreatorId(PROJECT_CREATOR_ID)
                        .build()
        );
        this.projectService.deleteProject(createdProject.getProjectId());
    }
    @Test
    @DirtiesContext
    public void updateProject_fullField() {
        UUID createdProjectId = this.projectService.createProject(
                CreateProjectCommandRequest.builder()
                        .projectName(PROJECT_NAME)
                        .projectDuration(PROJECT_DURATION)
                        .projectCreatorId(PROJECT_CREATOR_ID)
                        .build()
        ).getProjectId();
        Assertions.assertEquals(
                this.projectService.updateProject(UpdateProjectCommandRequest.builder()
                                .projectName(PROJECT_NAME_CHANGED)
                                .projectDuration(PROJECT_DURATION_CHANGED)
                                .projectCreatorId(PROJECT_CREATOR_ID_CHANGED)
                        .build(), createdProjectId),
                UpdateProjectCommandResponse.builder()
                        .projectId(createdProjectId)
                        .projectName(PROJECT_NAME_CHANGED)
                        .projectDuration(PROJECT_DURATION_CHANGED)
                        .projectCreatorId(PROJECT_CREATOR_ID_CHANGED)
                        .build()
        );
        this.projectService.deleteProject(createdProjectId);
    }
    @Test
    @DirtiesContext
    public void listProject() {
        Assertions.assertNotNull(this.projectService.listProject());
    }
    @Test
    @DirtiesContext
    public void detailProject() {
        UUID createdProjectId = this.projectService.createProject(
                CreateProjectCommandRequest.builder()
                        .projectName(PROJECT_NAME)
                        .projectDuration(PROJECT_DURATION)
                        .projectCreatorId(PROJECT_CREATOR_ID)
                        .build()
        ).getProjectId();
        Assertions.assertNotNull(this.projectService.detailProject(createdProjectId));
        this.projectService.deleteProject(createdProjectId);
    }
    @Test
    @DirtiesContext
    public void deleteProject() {
        UUID createdProjectId = this.projectService.createProject(
                CreateProjectCommandRequest.builder()
                        .projectName(PROJECT_NAME)
                        .projectDuration(PROJECT_DURATION)
                        .projectCreatorId(PROJECT_CREATOR_ID)
                        .build()
        ).getProjectId();
        this.projectService.deleteProject(createdProjectId);
        try {
            this.projectService.detailProject(createdProjectId);
            Assertions.assertTrue(false);
        }catch (IllegalArgumentException ex) {
            Assertions.assertTrue(true);
        }
    }
}
