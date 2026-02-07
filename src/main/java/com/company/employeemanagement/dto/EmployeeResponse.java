package com.company.employee_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String department;
    private String role;
}

