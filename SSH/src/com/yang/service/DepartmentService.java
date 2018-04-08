package com.yang.service;

import com.yang.dao.DepartmentDao;
import com.yang.entities.DepartmentEntity;

import java.util.List;

public class DepartmentService {
    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<DepartmentEntity>getAll(){
        return departmentDao.getAll();
    }
}
