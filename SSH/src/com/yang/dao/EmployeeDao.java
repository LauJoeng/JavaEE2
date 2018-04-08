package com.yang.dao;

import com.yang.entities.EmployeeEntity;

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
}
