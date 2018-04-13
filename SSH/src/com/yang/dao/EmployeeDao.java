package com.yang.dao;

import com.yang.entities.EmployeeEntity;
import org.hibernate.Query;

import java.util.List;

public class EmployeeDao extends BaseDao{
    public List<EmployeeEntity>getAll(){
        String hql = "FROM EmployeeEntity e LEFT OUTER JOIN FETCH e.department";
        return getSession().createQuery(hql).list();
    }

    public void delete(Integer id){
        String hql = "DELETE FROM EmployeeEntity e WHERE e.id = ?0";
        getSession().createQuery(hql).setParameter("0",id).executeUpdate();
    }

    public void saveOrUpdate(EmployeeEntity employee){
        getSession().saveOrUpdate(employee);
    }

    public EmployeeEntity getEmployeeByLastName(String lastName){
        String hql = "FROM EmployeeEntity e WHERE e.lastName = ?0";
        Query query = getSession().createQuery(hql).setParameter("0",lastName);
        return (EmployeeEntity) query.uniqueResult();
    }

    public EmployeeEntity get(Integer id){
        return (EmployeeEntity) getSession().get(EmployeeEntity.class,id);
    }
}
