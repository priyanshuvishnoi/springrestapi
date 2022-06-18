package dev.priyanshuvishnoi.springrestapi.controller;

import dev.priyanshuvishnoi.springrestapi.models.Employee;
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

    @Autowired
    public EmployeeController(EmployeeService eService) {
        this.eService = eService;
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
    public List<Employee> getEmployees() {
        return eService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return eService.getEmployee(id);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(eService.saveEmployee(employee), HttpStatus.CREATED);
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
}
