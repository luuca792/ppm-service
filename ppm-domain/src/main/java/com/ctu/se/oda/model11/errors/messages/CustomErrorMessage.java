package com.ctu.se.oda.model11.errors.messages;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomErrorMessage {
    public final static String NOT_FOUND = "Can not found this entity by this id!";
}
