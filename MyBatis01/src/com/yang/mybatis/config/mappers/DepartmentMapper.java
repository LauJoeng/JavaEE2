package com.yang.mybatis.config.mappers;

import com.yang.mybatis.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    Department getDepartmentByIdPlus(Integer id);
}
