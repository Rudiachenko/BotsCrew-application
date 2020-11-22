package application.service;

import application.model.Department;
import java.util.List;

public interface DepartmentService {
    Department addData(Department department);

    Department findByName(String name);

    List<Department> findAll();
}
