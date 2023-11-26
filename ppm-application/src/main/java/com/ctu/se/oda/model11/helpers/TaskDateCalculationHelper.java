package com.ctu.se.oda.model11.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctu.se.oda.model11.daos.IProjectService;
import com.ctu.se.oda.model11.daos.ITaskDependencyService;
import com.ctu.se.oda.model11.daos.ITaskService;
import com.ctu.se.oda.model11.enums.DependencyType;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.interfaces.ITaskApplication;
import com.ctu.se.oda.model11.models.commands.requests.task.UpdateTaskCommandRequest;
import com.ctu.se.oda.model11.models.dtos.TaskDTO;
import com.ctu.se.oda.model11.models.dtos.TaskDependencyDTO;
import com.ctu.se.oda.model11.models.queries.responses.project.RetrieveProjectQueryResponse;

@Service
public class TaskDateCalculationHelper {

	@Autowired
	ITaskApplication taskApplication;

	@Autowired
	IProjectService projectService;

	@Autowired
	ITaskService taskService;

	@Autowired
	ITaskDependencyService taskDependencyService;

	/**
	 * Update start date and end date for tasks of a project based on their
	 * topological order
	 * 
	 * @param projectId the project ID of project to schedule
	 */
	public void dateScheduler(UUID projectId) {
		RetrieveProjectQueryResponse projectQueryResponse = projectService.getProjectById(projectId);
		if (Objects.isNull(projectQueryResponse.getProjectStartAt())) {
			throw new InternalServerErrorException(CustomErrorMessage.PROJECT_START_DATE_NOT_FOUND);
		}
		LocalDate projectStartDate = projectQueryResponse.getProjectStartAt();
		List<UUID> order = topologicalSort(constructGraph(projectId));
		for (UUID currentTaskId : order) {
			TaskDTO currentTask = taskApplication.getTaskById(currentTaskId);
			List<TaskDependencyDTO> dependentTasks = taskDependencyService.getDependentTasks(currentTask.getTaskId());

			Map<UUID, DependencyType> dependencies = new HashMap<>();
			for (TaskDependencyDTO taskDependencyDTO : dependentTasks) {
				dependencies.put(taskDependencyDTO.getDependentTaskId(), taskDependencyDTO.getDependencyType());
			}
			UpdateTaskCommandRequest updatedTask = UpdateTaskCommandRequest.builder().build();
			updatedTask.setTaskId(currentTaskId);
			if (dependencies.size() == 0) {
				updatedTask.setTaskStartAt(projectStartDate);
				LocalDate endDate = projectStartDate.plusDays(currentTask.getTaskDuration().longValue());
				updatedTask.setTaskEndAt(endDate);
			} else {
				LocalDate maxEarlyStartDate = LocalDate.MIN;
				LocalDate startDate = LocalDate.MIN;
				for (Map.Entry<UUID, DependencyType> dependency : dependencies.entrySet()) {
					TaskDTO currentDependency = taskApplication.getTaskById(dependency.getKey());
					if (dependency.getValue().equals(DependencyType.SS)) {
						startDate = currentDependency.getTaskStartAt();
					} else if (dependency.getValue().equals(DependencyType.FS)) {
						startDate = currentDependency.getTaskEndAt();
					}
					if (startDate.isAfter(maxEarlyStartDate)) {
						maxEarlyStartDate = startDate;
					}
				}
				updatedTask.setTaskStartAt(maxEarlyStartDate);
				LocalDate endDate = maxEarlyStartDate.plusDays(currentTask.getTaskDuration().longValue());
				updatedTask.setTaskEndAt(endDate);
			}
			taskService.updateTask(updatedTask);
		}
	}

	/**
	 * Sort tasks of project in topological order
	 * 
	 * @param graph the graph contain tasks to sort
	 * @return List<Integer> the list of task ID in topological order
	 */
	private List<UUID> topologicalSort(Map<UUID, TaskDTO> graph) {
		Integer numberOfTasks = graph.size();
		List<UUID> ordering = new ArrayList<UUID>(Arrays.asList(new UUID[numberOfTasks]));
		Map<UUID, Boolean> visited = new HashMap<UUID, Boolean>();
		for (UUID taskId : graph.keySet()) {
			visited.put(taskId, Boolean.FALSE);
		}
		Integer orderIndex = numberOfTasks - 1;

		for (UUID taskId : graph.keySet()) {
			if (visited.get(taskId) == Boolean.FALSE) {
				List<UUID> visitedNodes = new ArrayList<>();
				dfs(taskId, visited, visitedNodes, graph);
				for (UUID nodeId : visitedNodes) {
					ordering.set(orderIndex, nodeId);
					orderIndex = orderIndex - 1;
				}
			}
		}
		return ordering;
	}

	/**
	 * Perform a depth first search from a task of a graph and update visited nodes
	 * 
	 * @param startingTaskId the task ID to start DFS
	 * @param visited
	 * @param visitedNodes
	 * @param graph          the graph contain tasks to perform DFS
	 *
	 */
	private void dfs(UUID startingTaskId, Map<UUID, Boolean> visited, List<UUID> visitedNodes,
			Map<UUID, TaskDTO> graph) {
		visited.put(startingTaskId, Boolean.TRUE);

		List<UUID> targets = new ArrayList<>();
		for (TaskDTO task : graph.values()) {
			List<TaskDependencyDTO> dependentTasks = taskDependencyService.getDependentTasks(task.getTaskId());
			for (TaskDependencyDTO dependency : dependentTasks) {
				if (dependency.getDependentTaskId().equals(startingTaskId)) {
					targets.add(dependency.getTaskId());
				}
			}
		}

		for (UUID target : targets) {
			if (visited.get(target) == Boolean.FALSE) {
				dfs(target, visited, visitedNodes, graph);
			}
		}
		visitedNodes.add(startingTaskId);
	}

	/**
	 * Construct a graph of tasks from a project
	 * 
	 * @param projectId the projectId
	 * @return Map<UUID, CreateTaskCommandRequest> graph
	 */
	private Map<UUID, TaskDTO> constructGraph(UUID projectId) {
		Map<UUID, TaskDTO> graph = new HashMap<>();
		List<TaskDTO> taskList = taskApplication.getTasksOfProject(projectId);
		for (TaskDTO task : taskList) {
			graph.put(task.getTaskId(), task);
		}
		return graph;
	}
}
