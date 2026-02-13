package srividya.employee_crud.service;

import org.springframework.stereotype.Service;
import srividya.employee_crud.entity.Employee;
import srividya.employee_crud.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Employee create(Employee employee) {
        return repo.save(employee);
    }

    // READ ALL
    public List<Employee> getAll() {
        return repo.findAll();
    }

    // READ BY ID
    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // UPDATE (full update)
    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        existing.setContact(updated.getContact());
        existing.setSalary(updated.getSalary());

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Increment salary for one employee
    public Employee incrementSalary(Long id, int amount) {
        Employee emp = getById(id);

        Integer current = emp.getSalary();
        if (current == null) current = 0;

        emp.setSalary(current + amount);
        return repo.save(emp);
    }

    // Increment salary for ALL employees
    public List<Employee> incrementSalaryForAll(int amount) {
        List<Employee> employees = repo.findAll();

        for (Employee emp : employees) {
            Integer current = emp.getSalary();
            if (current == null) current = 0;

            emp.setSalary(current + amount);
        }

        return repo.saveAll(employees);
    }
}
