package dev.priyanshuvishnoi.springrestapi.services;

import dev.priyanshuvishnoi.springrestapi.exceptions.NotFoundException;
import dev.priyanshuvishnoi.springrestapi.models.Employee;
import dev.priyanshuvishnoi.springrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public List<Employee> getEmployees(Integer page, Integer size) {
        var pages = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        return employeeRepo.findAll(pages).getContent();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        }

        throw new NotFoundException("Employee not found!");
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        var empl = getEmployee(employee.getId());
        var cls = empl.getClass();
        var fields = cls.getDeclaredFields();
        Employee result;

        try {
            result = cls.getDeclaredConstructor().newInstance();
            for (var field : fields) {
                field.setAccessible(true);
                var val1 = field.get(empl);
                var val2 = field.get(employee);
                var value = (val2 != null) ? val2 : val1;

                field.set(result, value);
            }
            return employeeRepo.save(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepo.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return employeeRepo.findAllByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(name, location);
    }

    @Override
    public List<Employee> getEmployeesByNameContaining(String keyword) {
        var sort = Sort.by(Sort.Direction.DESC, "id");
        return employeeRepo.findAllByNameIsContainingIgnoreCase(keyword, sort);
    }

    @Override
    public List<Employee> getEmployeesByNameOrLocation(String name, String location) {
        return employeeRepo.getEmployeesByNameAndLocation(name, location);
    }

    @Override
    public Integer deleteByEmployeeName(String name) {
        return employeeRepo.deleteByEmployeeName(name);
    }
}
