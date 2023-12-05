package com.ctu.se.oda.model11.helpers;

import com.ctu.se.oda.model11.daos.ProjectDAO;
import com.ctu.se.oda.model11.daos.TaskDAO;
import com.ctu.se.oda.model11.enums.ProjectStatus;
import com.ctu.se.oda.model11.enums.TaskStatus;
import com.ctu.se.oda.model11.models.commands.requests.project.UpdateProjectCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;
import com.ctu.se.oda.model11.models.queries.responses.task.RetrieveTaskQueryResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class StatusUpdateHelper {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Scheduled(cron = "*/10 * * * * *")
    public void statusUpdateJob() {
        int projectUpdated = 0;
        int taskUpdated = 0;

        List<RetrieveProjectQueryResponse> projects = projectDAO.getAllProjects(null, null);
        for (RetrieveProjectQueryResponse project : projects) {
            if (Objects.nonNull(project.getProjectStartAt())
                && !project.getProjectStatus().equals(ProjectStatus.IN_PROGRESS)
                && !project.getProjectStatus().equals(ProjectStatus.DONE)
                && (project.getProjectStartAt().equals(LocalDate.now())
                || project.getProjectStartAt().isBefore(LocalDate.now()))) {

                UpdateProjectCommandRequest updatedProjectRequest
                        = UpdateProjectCommandRequest.builder()
                            .projectId(project.getProjectId())
                            .projectStatus(ProjectStatus.IN_PROGRESS)
                            .build();

                projectDAO.updateProject(updatedProjectRequest);
                projectUpdated += 1;
            }
            //update status complete to project when end date >= today date
            if (Objects.nonNull(project.getProjectEndAt())
                    && !project.getProjectStatus().equals(ProjectStatus.DONE)
                    && (project.getProjectEndAt().equals(LocalDate.now())
                    || project.getProjectEndAt().isBefore(LocalDate.now()))) {
                UpdateProjectCommandRequest updatedProjectRequest
                        = UpdateProjectCommandRequest.builder()
                        .projectId(project.getProjectId())
                        .projectStatus(ProjectStatus.DONE)
                        .build();

                projectDAO.updateProject(updatedProjectRequest);
                projectUpdated += 1;
            }

        }

        List<RetrieveTaskQueryResponse> tasks = taskDAO.getAllTasks(null);
        for (RetrieveTaskQueryResponse task : tasks) {
            if (Objects.nonNull(task.getTaskStartAt())
                    && !task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)
                    && !task.getTaskStatus().equals(TaskStatus.DONE)
                    && (task.getTaskStartAt().equals(LocalDate.now())
                    || task.getTaskStartAt().isBefore(LocalDate.now()))) {

                UpdateTaskCommandRequest updatedTaskRequest
                        = UpdateTaskCommandRequest.builder()
                        .taskId(task.getTaskId())
                        .taskStatus(TaskStatus.IN_PROGRESS)
                        .build();
                taskDAO.updateTask(updatedTaskRequest);
                taskUpdated += 1;
            }

            if (Objects.nonNull(task.getTaskEndAt())
                    && !task.getTaskStatus().equals(TaskStatus.DONE)
                    && (task.getTaskEndAt().equals(LocalDate.now())
                    || task.getTaskEndAt().isBefore(LocalDate.now()))) {

                UpdateTaskCommandRequest updatedTaskRequest
                        = UpdateTaskCommandRequest.builder()
                        .taskId(task.getTaskId())
                        .taskStatus(TaskStatus.DONE)
                        .build();
                taskDAO.updateTask(updatedTaskRequest);
                taskUpdated += 1;
            }
        }

        log.info("Update status job executed: {} projects updated, {} tasks updated", projectUpdated, taskUpdated);
    }

}
