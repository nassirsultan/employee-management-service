package com.company.employee_management_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.employee_management_service.dto.EmployeeRequest;
import com.company.employee_management_service.dto.EmployeeResponse;
import com.company.employee_management_service.model.Employee;
import com.company.employee_management_service.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository repository;

	@Override
	public EmployeeResponse create(EmployeeRequest request) {
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDepartment(request.getDepartment());
		employee.setRole(request.getRole());

		Employee saved = repository.save(employee);
		return new EmployeeResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getDepartment(),
				saved.getRole());
	}

	@Override
	public List<EmployeeResponse> getAll() {

		return repository.findAll().stream()
				.map(e -> new EmployeeResponse(e.getId(), e.getName(), e.getEmail(), e.getDepartment(), e.getRole()))
				.toList();
	}

}
