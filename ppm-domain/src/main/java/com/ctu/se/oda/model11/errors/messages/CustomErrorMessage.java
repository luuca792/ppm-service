package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.StringJoiner;

@NoArgsConstructor
@Data
public class CustomErrorMessage {
    public static String NOT_FOUND_BY_ID = "Can not found this entity by ID";
    public static String CANNOT_CREATE_IF_PROJECT_DO_NOT_EXIST = "Project ID do not exist, try it again";
}
