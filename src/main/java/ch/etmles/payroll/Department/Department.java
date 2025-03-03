package ch.etmles.payroll.Department;

import ch.etmles.payroll.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Department {
    private @Id
    @GeneratedValue Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees;

    public Department(){}

    public Department(String name) {
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Department department))
            return false;
        return Objects.equals(this.id, department.id)
                && Objects.equals(this.name, department.name)
                && Objects.equals(this.employees, department.employees);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.employees);
    }

    @Override
    public String toString(){
        return "Department{" +
                "id='" + this.id + "', " +
                "name='" + this.name + "', " +
                "employees=" + (employees != null ? employees.size() : 0) +
                '}';
    }
}
