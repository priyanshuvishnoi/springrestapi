package dev.priyanshuvishnoi.springrestapi.services;

import dev.priyanshuvishnoi.springrestapi.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees(Integer page, Integer number);

    Employee saveEmployee(Employee employee);

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByNameContaining(String keyword);

    List<Employee> getEmployeesByNameOrLocation(String name, String location);

    Integer deleteByEmployeeName(String name);
}
