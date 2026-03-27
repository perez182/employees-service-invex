package com.invex.employees.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invex.employees.service.dto.request.EmployeeCreateBulkRequest;
import com.invex.employees.service.dto.request.EmployeeCreateRequest;
import com.invex.employees.service.dto.response.EmployeeResponse;
import com.invex.employees.service.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("employee")
@Validated
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping()
    @Operation(summary="get all employees")
    public ResponseEntity<List<EmployeeResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeByID(id));
    }

    @GetMapping("searchName/{name}")
    @Operation(summary = "search employee by name, just match with first name or middle name ")
    public ResponseEntity<List<EmployeeResponse>> getMethodName(@PathVariable String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
    
    @PostMapping("/bulk")
    public  ResponseEntity<List<EmployeeResponse>>  createBulk(@Valid @RequestBody EmployeeCreateBulkRequest req) {
        return new ResponseEntity<>(service.createBulk(req),HttpStatus.CREATED);

    }
    
    @PostMapping()
    @Operation(summary = "create worker")
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeCreateRequest req){
       return new ResponseEntity<>(service.create(req),HttpStatus.CREATED);
    }
    

}
