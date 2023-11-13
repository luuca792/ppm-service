package com.ctu.se.oda.model11.utils;

import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;

import java.lang.reflect.Field;

public class NonNullPropertiesUtils {

    public static void copyNonNullProperties(Object request, Object response) {
        Class<?> requestClass = request.getClass();
        Class<?> responseClass = response.getClass();

        for (Field requestField : requestClass.getDeclaredFields()) {
            try {
                requestField.setAccessible(true);
                Object requestValue = requestField.get(request);

                if (requestValue != null) {
                    Field responseField = responseClass.getDeclaredField(requestField.getName());
                    responseField.setAccessible(true);
                    responseField.set(response, requestValue);
                }
            } catch (Exception e) {
                throw new InternalServerErrorException(CustomErrorMessage.CAN_NOT_COPY_PROPERTIES);
            }
        }
    }
}

