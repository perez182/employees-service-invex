package com.invex.employees.service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.invex.employees.service.domain.Employee;
import com.invex.employees.service.dto.request.EmployeeCreateBulkRequest;
import com.invex.employees.service.dto.request.EmployeeCreateRequest;
import com.invex.employees.service.dto.request.EmployeeUpdateRequest;
import com.invex.employees.service.dto.response.EmployeeResponse;
import com.invex.employees.service.mapper.EmployeeMapper;
import com.invex.employees.service.repository.Employee.EmployeeRepository;
import com.invex.employees.service.service.impl.EmployeeServiceImp;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImpTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @InjectMocks
    private EmployeeServiceImp employeeService;

    @Test
    void getEmployeeByIDIdExists() {
   
        Long id = 1L;
        Employee employee = new Employee();
        EmployeeResponse response = new EmployeeResponse();
        
        when(repository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.toEmployeeResponse(employee)).thenReturn(response);

        EmployeeResponse result = employeeService.getEmployeeByID(id);
      
        assertNotNull(result);
        verify(repository).findById(id);
    }

    @Test
    void getEmployeeByIDTHROWS() {
       
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
  
        assertThrows(EntityNotFoundException.class, () -> {
            employeeService.getEmployeeByID(id);
        });
    }

    @Test
    void createShouldSuccessful() {
       
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        Employee employee = new Employee();
        EmployeeResponse response = new EmployeeResponse();

        when(mapper.toEntityCreate(request)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);
        when(mapper.toEmployeeResponse(employee)).thenReturn(response);
        
        EmployeeResponse result = employeeService.create(request);

        assertNotNull(result);
        verify(repository).save(any(Employee.class));
    }

    @Test
    void deleteShouldCallRepository() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        
        employeeService.delete(id);
        
        verify(repository).deleteById(id);
    }

    @Test
    void deleteShouldThrowException() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            employeeService.delete(id);
        });
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void searchByName() {
        String name = "Java";
        List<Employee> list = List.of(new Employee());
        when(repository.findByNameParcial(name)).thenReturn(list);

        List<EmployeeResponse> result = employeeService.searchByName(name);

        assertNotNull(result);
        verify(repository).findByNameParcial(name);
    }

    @Test
    void getAllReturnList() {

        List<Employee> employees = List.of(new Employee(), new Employee());
        when(repository.findAll()).thenReturn(employees);
        when(mapper.toEmployeeResponse(any(Employee.class))).thenReturn(new EmployeeResponse());

        List<EmployeeResponse> result = employeeService.getAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void updateWhenIdExists() {
        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        request.setId(1L);
        Employee existingEmployee = new Employee();
        Employee savedEmployee = new Employee();
        EmployeeResponse response = new EmployeeResponse();

        when(repository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(repository.save(existingEmployee)).thenReturn(savedEmployee);
        when(mapper.toEmployeeResponse(savedEmployee)).thenReturn(response);

        EmployeeResponse result = employeeService.update(request);

        assertNotNull(result);
        verify(mapper).update(eq(request), eq(existingEmployee)); 
        verify(repository).save(existingEmployee);
    }



    @Test
    void createBulkSaveListIsPresent() {
        EmployeeCreateBulkRequest request = new EmployeeCreateBulkRequest();
        request.setEmployees(List.of(new EmployeeCreateRequest()));
        List<Employee> entities = List.of(new Employee());
        
        when(mapper.toEntityCreate(any())).thenReturn(new Employee());
        when(repository.saveAll(any())).thenReturn(entities);
        when(mapper.toEmployeeResponse(any())).thenReturn(new EmployeeResponse());

        List<EmployeeResponse> result = employeeService.createBulk(request);

        assertEquals(1, result.size());
        verify(repository).saveAll(anyList());
    }


}
