package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomErrorMessage {
	public static String PROJECT_ID_NOT_FOUND = "Project does not exist";
	public static String TASK_ID_NOT_FOUND = "Task does not exist";
	public static String MATERIAL_ID_NOT_FOUND = "Material does not exist";
	public static String ENTITY_NOT_FOUND = "Entity not found";
	public static String START_DATE_AFTER_END_DATE = "Invalid start date";
	public static String PARENT_TASK_ID_DO_NOT_EXIST = "Parent task does not exist";
	public static String MATERIAL_TYPE_ID_NOT_FOUND = "Material type does not exist";
	public static String PROJECT_START_DATE_NOT_FOUND = "Project start date is required for scheduling";
	public static String TASK_DEPENDENCY_ID_NOT_FOUND = "Task dependency id not found";
	public static String SUBTASK_ID_NOT_FOUND = "Subtask id not found";
}
