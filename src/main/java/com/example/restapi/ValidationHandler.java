package com.example.restapi;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {

  @ExceptionHandler(ConstraintViolationException.class)//処理したい例外
  public ResponseEntity<Map<String, List<String>>> handleConstraintViolationException(
      ConstraintViolationException ex) {
    Map<String, List<String>> errors = new HashMap<>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      String fieldName = violation.getRootBeanClass().getName().toString();
      String errorMessage = violation.getMessage();
      errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(errorMessage);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
