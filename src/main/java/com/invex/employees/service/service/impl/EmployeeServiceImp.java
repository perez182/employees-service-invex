package com.invex.employees.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.invex.employees.service.mapper.EmployeeMapper;
import com.invex.employees.service.repository.Employee.EmployeeRepository;
import com.invex.employees.service.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import com.invex.employees.service.domain.Employee;
import com.invex.employees.service.dto.request.EmployeeCreateBulkRequest;
import com.invex.employees.service.dto.request.EmployeeCreateRequest;
import com.invex.employees.service.dto.request.EmployeeUpdateRequest;
import com.invex.employees.service.dto.response.EmployeeResponse;

@Slf4j
@Service
@Data
@AllArgsConstructor
@Transactional
public class EmployeeServiceImp implements EmployeeService{

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeResponse> getAll() {
        return repository.findAll().stream().map(mapper::toEmployeeResponse).toList();
    }

    @Override
    public EmployeeResponse getEmployeeByID(Long id) {
        return repository.findById(id)
        .map(mapper::toEmployeeResponse)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EmployeeResponse> createBulk(EmployeeCreateBulkRequest req) {
        log.info("Create bulk employees: ", req.getEmployees().size());
        if(req.getEmployees()==null){
            return new ArrayList<>();
        }

        log.info(req.getEmployees().toString());
        List<Employee > employees = repository.saveAll(
            req.getEmployees().stream().map(mapper::toEntityCreate).toList()
        );
        return employees.stream().map(mapper::toEmployeeResponse).toList();
    }

    @Override
    public EmployeeResponse create(EmployeeCreateRequest req) {
        Employee employee = repository.save(mapper.toEntityCreate(req));
        return mapper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse update(EmployeeUpdateRequest req) {
        //Search Entity
        Employee existing = repository.findById(req.getId())
                .orElseThrow(() -> new EntityNotFoundException("Update failed. Employee ID " + req.getId() + " not found."));
        
        // Update fields from dto to entity
        mapper.update(req, existing);
        
        return mapper.toEmployeeResponse(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Delete failed. Employee ID " + id + " not found.");
        }
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeResponse> searchByName(String name) {
        List<Employee> employees = repository.findByNameParcial(name);
        return employees.stream().map(mapper::toEmployeeResponse).toList();
    }
}
