package dev.priyanshuvishnoi.springrestapi.repository;

import dev.priyanshuvishnoi.springrestapi.models.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findAllByNameContainingIgnoreCase(String name);

    List<Employee> findAllByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name, String location);

    List<Employee> findAllByNameIsContainingIgnoreCase(String keyword, Sort sort);

    @Query("FROM Employee WHERE upper(name) = upper(:name) OR upper(location) = upper(:location)")
    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee WHERE upper(name) = upper(:name)")
    Integer deleteByEmployeeName(String name);
}
