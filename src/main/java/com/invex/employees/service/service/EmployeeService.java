package com.invex.employees.service.service;

import java.util.List;

import com.invex.employees.service.dto.response.EmployeeResponse;
import com.invex.employees.service.dto.request.EmployeeCreateBulkRequest;
import com.invex.employees.service.dto.request.EmployeeCreateRequest;
import com.invex.employees.service.dto.request.EmployeeUpdateRequest;

public interface EmployeeService {

     List<EmployeeResponse> getAll();
     EmployeeResponse getEmployeeByID(Long id);

     List<EmployeeResponse> createBulk(EmployeeCreateBulkRequest req);

     EmployeeResponse create(EmployeeCreateRequest req);

     EmployeeResponse update(EmployeeUpdateRequest req);

     void delete(Long id);

     List<EmployeeResponse> searchByName(String name);
    
}
