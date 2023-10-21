package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CustomErrorMessage {
    public static String CANNOT_CREATE_IF_PROJECT_DO_NOT_EXIST = "Project ID doesn't exist, please try again";
    public static String NOT_FOUND_BY_ID = "Can not found this entity by ID";
}
