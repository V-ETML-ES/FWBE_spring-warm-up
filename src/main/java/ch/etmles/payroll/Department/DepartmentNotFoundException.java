package ch.etmles.payroll.Controllers;

public class DepartmentNotFoundException extends RuntimeException {
    DepartmentNotFoundException(Long id){
        super("Could not find department " + id);
    }
}
