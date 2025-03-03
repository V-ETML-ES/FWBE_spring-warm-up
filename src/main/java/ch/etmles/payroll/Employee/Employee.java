package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Employee {

    private @Id
    @GeneratedValue Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String firstname;
    private String role;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    public Employee(){}

    public Employee(String email, String name, String firstname, String role, Department department) {
        this.setEmail(email);
        this.setName(name);
        this.setFirstname(firstname);
        this.setRole(role);
        this.setDepartment(department);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFirstName(){
        return this.firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Employee employee))
            return false;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.email, employee.email)
                && Objects.equals(this.name, employee.name)
                && Objects.equals(this.firstname, employee.firstname)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.email, this.name, this.firstname, this.role);
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" + this.getId() + ", email='" + this.getEmail() + '\'' + ", name='" + this.getName() + '\'' + ", firstname='" + this.getFirstName() + '\'' + ", role='" + this.getRole() + '\'' + '}';
    }
}
