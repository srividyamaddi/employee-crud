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
        if (employee == null) {
            throw new RuntimeException("Request body is missing");
        }
        employee.setId(null);
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

    // UPDATE (contact will NOT be updated)
    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        // existing.setContact(updated.getContact()); // keep contact fixed

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
