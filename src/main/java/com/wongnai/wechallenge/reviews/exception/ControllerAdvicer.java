package com.wongnai.wechallenge.reviews.exception;

import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvicer {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleException(Exception ioe) {
        Map<String, Object> body = new HashMap<>();
        body.put("errorMessage", "Unexpected error has occured.");
        return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
