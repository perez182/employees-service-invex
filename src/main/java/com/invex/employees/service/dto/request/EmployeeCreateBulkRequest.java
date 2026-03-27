package com.invex.employees.service.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Valid
public class EmployeeCreateBulkRequest {
    @NotEmpty
    List<EmployeeCreateRequest> employees;
    
}
