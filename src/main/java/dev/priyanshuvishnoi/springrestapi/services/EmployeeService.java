package dev.priyanshuvishnoi.springrestapi.services;

import dev.priyanshuvishnoi.springrestapi.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
