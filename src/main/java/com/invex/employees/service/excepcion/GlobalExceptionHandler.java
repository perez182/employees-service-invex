package com.invex.employees.service.excepcion;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> details = ex.getBindingResult()
            .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("field", error.getField()); 
                    map.put("error", error.getDefaultMessage());
                    return map;
                }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("errors", details);
        
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, List<String>>> handleReadableException(HttpMessageNotReadableException ex) {
        Map<String, List<String>> response = new HashMap<>();
        String error="Invalid format in one or more fields (check the fields birhtdate,age,isActive)";
        List<String> errors = new ArrayList<>();
        errors.add(error);
        response.put("errors", errors);
        
        return ResponseEntity.badRequest().body(response);
    }


}
