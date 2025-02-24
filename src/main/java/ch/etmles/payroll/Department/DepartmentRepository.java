package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
