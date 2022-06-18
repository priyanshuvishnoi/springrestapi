package dev.priyanshuvishnoi.springrestapi.repository;

import dev.priyanshuvishnoi.springrestapi.models.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findAllByNameContainingIgnoreCase(String name);

    List<Employee> findAllByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name, String location);

    List<Employee> findAllByNameIsContainingIgnoreCase(String keyword, Sort sort);

}
