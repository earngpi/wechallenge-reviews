package com.wongnai.wechallenge.reviews.exception;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvicer {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("errorMessage", "Unexpected error has occurred.");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataAccessResourceFailureException.class})
    protected ResponseEntity<Object> handleDataAccessResourceFailureException(DataAccessResourceFailureException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("errorMessage", "Datasource error has occurred.");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("errorMessage", e.getStatusText());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ReviewNotFoundException.class})
    protected ResponseEntity<Object> ReviewNotFoundException(ReviewNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("errorMessage", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
