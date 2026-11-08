package com.company.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EmployeeResponse implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String department;
    private String role;
}

