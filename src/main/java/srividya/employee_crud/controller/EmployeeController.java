package srividya.employee_crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import srividya.employee_crud.entity.Employee;
import srividya.employee_crud.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return service.create(employee);
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,
                           @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // INCREMENT SALARY FOR ONE EMPLOYEE
    @PutMapping("/{id}/increment")
    public Employee incrementSalary(
            @PathVariable Long id,
            @RequestParam int amount) {
        return service.incrementSalary(id, amount);
    }

    // INCREMENT SALARY FOR ALL EMPLOYEES
    @PutMapping("/increment-all")
    public List<Employee> incrementSalaryForAll(
            @RequestParam int amount) {
        return service.incrementSalaryForAll(amount);
    }
}
