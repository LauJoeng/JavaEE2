package com.yang.dao;

import com.yang.entities.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

    public List<EmployeeEntity>getAll(){
        String hql = "FROM EmployeeEntity e LEFT OUTER JOIN FETCH e.department";
        return getSession().createQuery(hql).list();
    }

    public void delete(Integer id){
        System.out.println(id);
        String hql = "DELETE FROM EmployeeEntity e WHERE e.id = ?0";
        getSession().createQuery(hql).setParameter("0",id).executeUpdate();
    }
}
