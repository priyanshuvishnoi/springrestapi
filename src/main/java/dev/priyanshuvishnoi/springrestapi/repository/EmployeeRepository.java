package dev.priyanshuvishnoi.springrestapi.repository;

import dev.priyanshuvishnoi.springrestapi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
