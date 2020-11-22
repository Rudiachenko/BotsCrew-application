package application.dao;

import application.model.Department;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao {
    Department addData(Department department);

    Department findByName(String name);

    List<Department> findAll();
}
