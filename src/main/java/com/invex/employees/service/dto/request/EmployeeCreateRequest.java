package com.invex.employees.service.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeRequest {
    @NotBlank(message = "fistName is required")
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String middleName;

    @NotBlank(message = "lastName is required")
    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String secondLastName;

    @Min(value=18 , message="Age must be >= 18")
    @Max(value = 120, message= "Age must be <=120")
    private Integer Age;

    @Pattern(regexp = "M|F", message = "Gender must be M or F")
    private String gender;

    @NotNull(message = "birtDate is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Size(max=50)
    private String position;

    private Boolean isActive;
}
