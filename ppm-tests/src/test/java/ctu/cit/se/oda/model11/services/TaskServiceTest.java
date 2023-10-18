package ctu.cit.se.oda.model11.services;

import com.ctu.se.oda.model11.Main;
import com.ctu.se.oda.model11.daos.ITaskService;
import org.junit.jupiter.api.Assertions;

import com.ctu.se.oda.model11.models.commands.requests.task.CreateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.task.UpdateTaskCommandResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@SpringBootTest(classes = Main.class)
@DirtiesContext
public class TaskServiceTest {
    private static final String TASK_NAME = "job_001";
    private static String TASK_DESCRIPTION = "job_to_easy";
    private static LocalDate TASK_START_AT = LocalDate.now();
    private static LocalDate TASK_END_AT = LocalDate.now().plus(30, ChronoUnit.DAYS);
    private static final String TASK_NAME_CHANGED = "job_001_01";
    private static String TASK_DESCRIPTION_CHANGED = "job_to_easy_01";
    private static LocalDate TASK_START_AT_CHANGED = LocalDate.now().plus(30, ChronoUnit.DAYS);
    private static LocalDate TASK_END_AT_CHANGED = TASK_START_AT_CHANGED.plus(30, ChronoUnit.DAYS);
    @Autowired
    private ITaskService taskService;
    @Test
    public void createTask_fullField() {
        var createdTask = this.taskService.createTask(CreateTaskCommandRequest.builder().taskName(TASK_NAME).taskDescription(TASK_DESCRIPTION).taskStartAt(TASK_START_AT).taskEndAt(TASK_END_AT).build());
        Assertions.assertNotNull(createdTask);
        this.taskService.deleteTask(createdTask.getTaskId());
    }
    @Test
    public void updateTask_fullField() {
        var createdTaskId = this.taskService.createTask(CreateTaskCommandRequest.builder().taskName(TASK_NAME).taskDescription(TASK_DESCRIPTION).taskStartAt(TASK_START_AT).taskEndAt(TASK_END_AT).build()).getTaskId();
        Assertions.assertEquals(this.taskService.updateTask(UpdateTaskCommandRequest.builder().taskName(TASK_NAME_CHANGED).taskDescription(TASK_DESCRIPTION_CHANGED).taskStartAt(TASK_START_AT_CHANGED).taskEndAt(TASK_END_AT_CHANGED).build(), createdTaskId), UpdateTaskCommandResponse.builder().taskId(createdTaskId).taskName(TASK_NAME_CHANGED).taskDescription(TASK_DESCRIPTION_CHANGED).taskStartAt(TASK_START_AT_CHANGED).taskEndAt(TASK_END_AT_CHANGED).build());
        this.taskService.deleteTask(createdTaskId);
    }
    @Test
    public void detailTask_success() {
        var createdTaskId = this.taskService.createTask(CreateTaskCommandRequest.builder().taskName(TASK_NAME).taskDescription(TASK_DESCRIPTION).taskStartAt(TASK_START_AT).taskEndAt(TASK_END_AT).build()).getTaskId();
        Assertions.assertNotNull(this.taskService.detailTask(createdTaskId));
        this.taskService.deleteTask(createdTaskId);
    }
    @Test
    public void listTask_success() {
        Assertions.assertNotNull(this.taskService.listTask());
    }
    @Test
    public void deleteTask() {
        var createdTaskId = this.taskService.createTask(CreateTaskCommandRequest.builder().taskName(TASK_NAME).taskDescription(TASK_DESCRIPTION).taskStartAt(TASK_START_AT).taskEndAt(TASK_END_AT).build()).getTaskId();
        this.taskService.deleteTask(createdTaskId);
        try {
            this.taskService.detailTask(createdTaskId);
            Assertions.assertFalse(true);
        }catch (IllegalArgumentException ex) {
            Assertions.assertTrue(true);
        }
    }
}
