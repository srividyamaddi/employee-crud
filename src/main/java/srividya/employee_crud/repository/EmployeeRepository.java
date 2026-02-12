package srividya.employee_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srividya.employee_crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
