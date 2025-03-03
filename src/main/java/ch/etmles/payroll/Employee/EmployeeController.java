package ch.etmles.payroll.Employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping
    List<Employee> all(){
        return repository.findAll();
    }

    @PostMapping
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @GetMapping("/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    Employee replaceEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setName(updatedEmployee.getName());
                    employee.setFirstname(updatedEmployee.getFirstName());
                    employee.setRole(updatedEmployee.getRole());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    updatedEmployee.setId(id);
                    return repository.save(updatedEmployee);
                });
    }

    @PatchMapping("/{id}")
    Employee modifyEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee){
        if(!repository.existsById(id)) throw new EmployeeNotFoundException(id);

        return repository.findById(id).map(employee -> {
            if (updatedEmployee.getName() != null) employee.setName(updatedEmployee.getName());
            if (updatedEmployee.getEmail() != null) employee.setEmail(updatedEmployee.getEmail());
            if (updatedEmployee.getFirstName() != null) employee.setFirstname(updatedEmployee.getFirstName());
            if (updatedEmployee.getRole() != null) employee.setRole(updatedEmployee.getRole());
            if (updatedEmployee.getDepartment() != null) employee.setDepartment(updatedEmployee.getDepartment());
            return repository.save(employee);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        if(!repository.existsById(id)) throw new EmployeeNotFoundException(id);
        repository.deleteById(id);
    }
}
