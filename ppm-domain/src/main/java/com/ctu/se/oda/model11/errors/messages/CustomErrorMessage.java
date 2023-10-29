package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomErrorMessage {

    public static String PROJECT_ID_DO_NOT_EXIST = "Project does not exist";
    public static String TASK_ID_DO_NOT_EXIST = "Task does not exist";
    public static String MATERIAL_ID_DO_NOT_EXIST = "Material does not exist";
    public static String NOT_FOUND_BY_ID = "Entity not found";
    public static String START_DATE_AFTER_END_DATE = "Invalid start date";
    public static String PARENT_TASK_ID_DO_NOT_EXIST = "Parent task does not exist";
}
