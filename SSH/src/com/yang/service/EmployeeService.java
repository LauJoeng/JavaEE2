package com.yang.service;

import com.yang.dao.EmployeeDao;
import com.yang.entities.EmployeeEntity;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<EmployeeEntity>getAll(){
        return employeeDao.getAll();
    }

    public void delete(Integer id){
        employeeDao.delete(id);
    }

    public void saveOrUpdate(EmployeeEntity employeeEntity){
        employeeDao.saveOrUpdate(employeeEntity);
    }

    public boolean lastNameIsValid(String lastName){
        return employeeDao.getEmployeeByLastName(lastName) == null;
    }

    public EmployeeEntity get(Integer id) {
        return employeeDao.get(id);
    }
}
