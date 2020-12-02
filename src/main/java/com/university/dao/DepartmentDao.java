package com.university.dao;

import com.university.model.Department;
import com.university.model.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao {
    Department save(Department department);

    Department findByName(String name);

    List<Department> findAll();

    List<Employee> findByContains(String template);
}
