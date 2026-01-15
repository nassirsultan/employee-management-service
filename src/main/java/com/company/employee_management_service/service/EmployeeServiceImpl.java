package com.company.employee_management_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.employee_management_service.dto.EmployeeRequest;
import com.company.employee_management_service.dto.EmployeeResponse;
import com.company.employee_management_service.exception.DuplicateResourceException;
import com.company.employee_management_service.exception.ResourceNotFoundException;
import com.company.employee_management_service.model.Employee;
import com.company.employee_management_service.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository repository;

	@Override
	public EmployeeResponse create(EmployeeRequest request) {
		
		if (repository.existsByEmail(request.getEmail())) {
	        throw new DuplicateResourceException(
	            "Employee already exists with email " + request.getEmail()
	        );
	    }
		
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDepartment(request.getDepartment());
		employee.setRole(request.getRole());

		Employee saved = repository.save(employee);
		return mapToResponse(saved);
	}

	@Override
	public List<EmployeeResponse> getAll() {

		return repository.findAll().stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	public EmployeeResponse getById(Long id) {
	    Employee employee = repository.findById(id)
	        .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Employee not found with id " + id
	            )
	        );
	    return mapToResponse(employee);
	}

	public void delete(Long id) {
	    Employee employee = repository.findById(id)
	        .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Employee not found with id " + id
	            )
	        );
	    repository.delete(employee);
	}

	private EmployeeResponse mapToResponse(Employee employee) {
	    return new EmployeeResponse(
	            employee.getId(),
	            employee.getName(),
	            employee.getEmail(),
	            employee.getDepartment(),
	            employee.getRole()
	    );
	}

}
