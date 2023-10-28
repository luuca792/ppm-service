package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CustomErrorMessage {

    public static String PROJECT_ID_DO_NOT_EXIST = "Project ID doesn't exist, please try again";
    public static String TASK_ID_DO_NOT_EXIST = "Task ID doesn't exist, please try again";
    public static String NOT_FOUND_BY_ID = "Can not found this entity by ID";
    public static String START_DATE_BEFORE_END_DATE = "Start Date need to be set before End Date, please try again";
    public static String PARENT_TASK_ID_DO_NOT_EXIST = "Parent task does not exist";
}
