package com.university.service.impl;

import com.university.dao.DepartmentDao;
import com.university.model.Department;
import com.university.model.Employee;
import com.university.service.DepartmentService;
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
    public Department save(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public Department findByName(String name) {
        return departmentDao.findByName(name);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public List<Employee> findByContains(String template) {
        return departmentDao.findByContains(template);
    }
}
