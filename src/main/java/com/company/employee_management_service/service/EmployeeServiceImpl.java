package com.company.employee_management_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<EmployeeResponse> getAll(int page, int size, String department, String role) {

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

		Page<Employee> employees;

		if (department != null && role != null) {
			employees = repository.findByDepartmentIgnoreCaseAndRoleIgnoreCase(department, role, pageable);
		} else if (department != null) {
			employees = repository.findByDepartmentIgnoreCase(department, pageable);
		} else if (role != null) {
			employees = repository.findByRoleIgnoreCase(role, pageable);
		} else {
			employees = repository.findAll(pageable);
		}

		return employees.map(this::mapToResponse);
	}

	@Override
	public EmployeeResponse getById(Long id) {
	    Employee employee = repository.findById(id)
	        .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Employee not found with id " + id
	            )
	        );
	    return mapToResponse(employee);
	}
	
	@Override
	public void delete(Long id) {
	    Employee employee = repository.findById(id)
	        .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Employee not found with id " + id
	            )
	        );
	    repository.delete(employee);
	}
	
	@Override
	public EmployeeResponse update(Long id, EmployeeRequest request) {

	    Employee employee = repository.findById(id)
	        .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Employee not found with id " + id
	            )
	        );

	    // optional: email change check
	    if (!employee.getEmail().equals(request.getEmail())
	            && repository.existsByEmail(request.getEmail())) {
	        throw new DuplicateResourceException(
	            "Employee already exists with email " + request.getEmail()
	        );
	    }

	    employee.setName(request.getName());
	    employee.setEmail(request.getEmail());
	    employee.setDepartment(request.getDepartment());
	    employee.setRole(request.getRole());

	    Employee updated = repository.save(employee);
	    return mapToResponse(updated);
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
