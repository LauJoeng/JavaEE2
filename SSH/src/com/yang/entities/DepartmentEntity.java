package com.yang.entities;

import java.util.Objects;

public class DepartmentEntity {
    private int id;
    private String departmentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return id == that.id &&
                Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, departmentName);
    }
}
