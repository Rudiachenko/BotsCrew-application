package com.university.dao.impl;

import com.university.dao.DepartmentDao;
import com.university.exceptions.DataProcessingException;
import com.university.model.Department;
import com.university.model.Employee;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private static final Logger logger = Logger.getLogger(Department.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Department save(Department department) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            logger.info("Department inserted successfully. " + department);
            return department;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert department "
                    + department.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Department findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Department> query = session.createQuery("from Department d "
                    + "join fetch d.employees "
                    + "where d.name = :name", Department.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Department> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department d "
                    + "left join fetch d.employees", Department.class).getResultList();
        }
    }

    @Override
    public List<Employee> findByContains(String template) {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee e "
                    + "where e.name like concat('%', :template, '%') or "
                    + "e.lastName like concat('%', :template, '%')", Employee.class);
            query.setParameter("template", template);
            return query.getResultList();
        }
    }
}
