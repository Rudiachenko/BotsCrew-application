package com.university.service;

import com.university.model.Department;
import com.university.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private final DepartmentService departmentService;

    @Autowired
    public DataInjector(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void injectData() {
        Employee bob = new Employee();
        bob.setName("Bob");
        bob.setLastName("Bobinsky");
        bob.setSalary(800d);
        bob.setTitle(Employee.Degree.PROFESSOR);

        Employee alice = new Employee();
        alice.setName("Alice");
        alice.setLastName("Alison");
        alice.setSalary(800d);
        alice.setTitle(Employee.Degree.PROFESSOR);

        Employee boris = new Employee();
        boris.setName("Boris");
        boris.setLastName("Borisov");
        boris.setSalary(700d);
        boris.setTitle(Employee.Degree.ASSISTANT);

        Department politology = new Department();
        politology.setName("politology");
        politology.setEmployees(List.of(bob, alice));
        politology.setHeadOfDepartment(bob);

        Department philosophy = new Department();
        philosophy.setName("philosophy");
        philosophy.setEmployees(List.of(bob, alice, boris));
        philosophy.setHeadOfDepartment(alice);

        departmentService.save(politology);
        departmentService.save(philosophy);
    }
}
