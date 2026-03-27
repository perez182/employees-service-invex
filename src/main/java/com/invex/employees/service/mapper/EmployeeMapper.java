package com.invex.employees.service.mapper;



import java.util.Optional;

import org.springframework.stereotype.Component;

import com.invex.employees.service.domain.Employee;
import com.invex.employees.service.dto.request.EmployeeCreateRequest;
import com.invex.employees.service.dto.request.EmployeeUpdateRequest;
import com.invex.employees.service.dto.response.EmployeeResponse;

@Component
public class EmployeeMapper {
        public Employee toEntityCreate(EmployeeCreateRequest req){
        Employee e = new Employee();
        e.setAge(req.getAge());
        e.setFirstName(req.getFirstName());
        e.setMiddleName(req.getMiddleName());
        e.setLastName(req.getLastName());
        e.setGender(req.getGender());
        e.setIsActive(req.getIsActive()?1:0);
        e.setBirthDate(req.getBirthDate());
        e.setPosition(req.getPosition());
        return e;
    }

    public EmployeeResponse toEmployeeResponse(Employee e){
        if(e==null){
            return null;            
        }
        return EmployeeResponse.builder().
        Age(e.getAge())
        .firstName(e.getFirstName())
        .middleName(e.getMiddleName())
        .lastName(e.getLastName())
        .secondLastName(e.getSecondLastName())
        .gender(e.getGender())
        .isActive(e.getIsActive()==1?true:false)
        .birthDate(e.getBirthDate())
        .position(e.getPosition())
        .createdAt(e.getCreatedAt())
        .id(e.getId())
        .build();
    }

    public void update(EmployeeUpdateRequest req, Employee existing) {
        Optional.ofNullable(req.getFirstName()).filter(s -> !s.isBlank()).ifPresent(existing::setFirstName);
        Optional.ofNullable(req.getMiddleName()).ifPresent(existing::setMiddleName);
        Optional.ofNullable(req.getLastName()).filter(s -> !s.isBlank()).ifPresent(existing::setLastName);
        Optional.ofNullable(req.getSecondLastName()).ifPresent(existing::setSecondLastName);
        Optional.ofNullable(req.getAge()).ifPresent(existing::setAge);
        Optional.ofNullable(req.getGender()).filter(s -> !s.isBlank()).ifPresent(existing::setGender);
        Optional.ofNullable(req.getBirthDate()).ifPresent(existing::setBirthDate);
        Optional.ofNullable(req.getPosition()).filter(s -> !s.isBlank()).ifPresent(existing::setPosition);
        Optional.ofNullable(req.getIsActive()).ifPresent(active -> existing.setIsActive(active ? 1 : 0));
    }
}
