package com.ctu.se.oda.model11.errors.exceptions;

import com.ctu.se.oda.model11.errors.responses.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(ErrorDetails.builder().detail(request.getDescription(false)).localDateTime(LocalDateTime.now()).message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
