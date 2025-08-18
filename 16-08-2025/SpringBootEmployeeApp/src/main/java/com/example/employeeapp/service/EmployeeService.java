package com.example.employeeapp.service;

import com.example.employeeapp.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> searchEmployeesByName(String name);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
