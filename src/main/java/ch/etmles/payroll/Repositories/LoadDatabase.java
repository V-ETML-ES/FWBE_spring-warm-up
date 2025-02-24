package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Department.DepartmentRepository;
import ch.etmles.payroll.Employee.Employee;
import ch.etmles.payroll.Employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository){
        return args->{
            // Création des départements
            Department itDepartment = new Department("Informatique");
            Department hrDepartment = new Department("Ressources Humaines");

            departmentRepository.save(itDepartment);
            departmentRepository.save(hrDepartment);

            log.info("Preloading " + itDepartment);
            log.info("Preloading " + hrDepartment);

            // Ajout des employés aux départements
            Employee bilbo = new Employee("test@gmail.com", "Bilbo", "Baggins", "burglar", itDepartment);
            Employee frodo = new Employee("abasd@outlook.com", "Frodo", "Baggins", "thief", hrDepartment);

            employeeRepository.save(bilbo);
            employeeRepository.save(frodo);

            log.info("Preloading " + bilbo);
            log.info("Preloading " + frodo);
        };
    }
}
