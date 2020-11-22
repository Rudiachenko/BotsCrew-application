package application.service.impl;

import application.dao.DepartmentDao;
import application.model.Department;
import application.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department addData(Department department) {
        return departmentDao.addData(department);
    }

    @Override
    public Department findByName(String name) {
        return departmentDao.findByName(name);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
