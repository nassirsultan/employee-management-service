package com.company.employee_management_service.service;

import org.springframework.data.domain.Page;

import com.company.employee_management_service.dto.EmployeeRequest;
import com.company.employee_management_service.dto.EmployeeResponse;

public interface EmployeeService {
	EmployeeResponse create(EmployeeRequest request);
	Page<EmployeeResponse> getAll(int page, int size, String department, String role);
	EmployeeResponse getById(Long id);
	void delete(Long id);
	EmployeeResponse update(Long id, EmployeeRequest request);
}
