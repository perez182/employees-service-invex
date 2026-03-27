package com.invex.employees.service.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;

    private Integer Age;
    private String gender;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String position;

    private LocalDateTime createdAt;
    private Boolean isActive;
}
