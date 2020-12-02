package com.university.service;

import com.university.model.Department;
import com.university.model.Employee;
import java.util.List;

public interface DepartmentService {
    Department addData(Department department);

    Department findByName(String name);

    List<Department> findAll();

    List<Employee> findByContains(String template);
}
