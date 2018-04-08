package com.yang.dao;

import com.yang.entities.DepartmentEntity;

import java.util.List;

public class DepartmentDao extends BaseDao{
    public List<DepartmentEntity>getAll(){
        String hql = "FROM DepartmentEntity";
        return getSession().createQuery(hql).list();
    }
}
