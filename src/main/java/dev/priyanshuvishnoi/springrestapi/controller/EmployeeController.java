package dev.priyanshuvishnoi.springrestapi.controller;

import dev.priyanshuvishnoi.springrestapi.models.Department;
import dev.priyanshuvishnoi.springrestapi.models.Employee;
import dev.priyanshuvishnoi.springrestapi.repository.DepartmentRepository;
import dev.priyanshuvishnoi.springrestapi.requests.EmployeeRequest;
import dev.priyanshuvishnoi.springrestapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService eService;

    private final DepartmentRepository deptRepo;

    @Autowired
    public EmployeeController(EmployeeService eService, DepartmentRepository deptRepo) {
        this.eService = eService;
        this.deptRepo = deptRepo;
    }

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppDetails() {
        return this.appName + " " + this.appVersion;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(eService.getEmployees(page, size));
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return eService.getEmployee(id);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest request) {
        Department dept = new Department(request.getDepartment());
        dept = deptRepo.save(dept);

        Employee emp = new Employee(request);
        emp.setDepartment(dept);

        return new ResponseEntity<>(eService.saveEmployee(emp), HttpStatus.CREATED);
    }

    @PatchMapping()
    public Employee updateEmployee(@RequestBody Employee employee) {
        return eService.updateEmployee(employee);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        eService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filterByName")
    public List<Employee> getEmployeesByName(@RequestParam String name) {
        return eService.getEmployeesByName(name);
    }

    @GetMapping("/filterByNameAndLocation")
    public List<Employee> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return eService.getEmployeesByNameAndLocation(name, location);
    }

    @GetMapping("/filterByNameContaining")
    public List<Employee> getEmployeesByNameAndLocation(@RequestParam String keyword) {
        return eService.getEmployeesByNameContaining(keyword);
    }

    @GetMapping("/{name}/{location}")
    public List<Employee> getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        return eService.getEmployeesByNameOrLocation(name, location);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
        return ResponseEntity.ok(eService.deleteByEmployeeName(name) + " records deleted!");
    }
}
