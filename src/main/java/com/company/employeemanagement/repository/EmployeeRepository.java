package com.company.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.employeemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);

	boolean existsByEmail(String email);

	Page<Employee> findByDepartmentIgnoreCase(String department, Pageable pageable);

	Page<Employee> findByRoleIgnoreCase(String role, Pageable pageable);

	Page<Employee> findByDepartmentIgnoreCaseAndRoleIgnoreCase(String department, String role, Pageable pageable);
}
