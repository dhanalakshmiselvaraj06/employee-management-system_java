package com.laxmisri6.employee_management_system.service;

import com.laxmisri6.employee_management_system.entity.Employee;
import com.laxmisri6.employee_management_system.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        employee.setId(null);
        return repository.save(employee);
    }
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(updatedEmployee.getName());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setEmail(updatedEmployee.getEmail());

        return repository.save(employee);
    }

    public void deleteEmployee(Long id) {

        repository.deleteById(id);
    }
}