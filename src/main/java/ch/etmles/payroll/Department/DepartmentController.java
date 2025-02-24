package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Department;
import ch.etmles.payroll.Repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository repository;

    DepartmentController(DepartmentRepository repository){
        this.repository = repository;
    }

    /**
     * Récupérer tous les départements
     */
    @GetMapping
    List<Department> all() {
        return this.repository.findAll();
    }

    /**
     * Créer un nouveau département
     */
    @PostMapping
    Department newDepartment(@RequestBody Department department) {
        return this.repository.save(department);
    }

    /**
     * Récupérer un département par ID
     */
    @GetMapping("/{id}")
    Department one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    /**
     * Mettre à jour un département existant
     */
    @PutMapping("/{id}")
    Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        return repository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    return repository.save(department);
                })
                .orElseGet(() -> {
                    updatedDepartment.setId(id);
                    return repository.save(updatedDepartment);
                });
    }

    /**
     * Supprimer un département
     */
    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable Long id) {
        if (!this.repository.existsById(id)) throw new DepartmentNotFoundException(id);
        this.repository.deleteById(id);
    }
}
