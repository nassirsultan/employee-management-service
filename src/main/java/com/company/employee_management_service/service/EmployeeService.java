package com.company.employee_management_service.service;

import java.util.List;

import com.company.employee_management_service.dto.EmployeeRequest;
import com.company.employee_management_service.dto.EmployeeResponse;

public interface EmployeeService {
	EmployeeResponse create(EmployeeRequest request);
	List<EmployeeResponse> getAll();
}
