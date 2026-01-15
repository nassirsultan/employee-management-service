package com.company.employee_management_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.employee_management_service.model.Employee;
import com.company.employee_management_service.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }
}

