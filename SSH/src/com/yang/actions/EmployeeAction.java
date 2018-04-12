package com.yang.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.yang.entities.EmployeeEntity;
import com.yang.service.DepartmentService;
import com.yang.service.EmployeeService;
import org.apache.struts2.interceptor.RequestAware;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<EmployeeEntity>,Preparable {
    private static final long serialVersionUID = 1L;

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public String list(){
        request.put("employees",employeeService.getAll());
        return "list";
    }

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }
    public String delete(){
        try {
            employeeService.delete(id);
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(inputStream == null);
        return "ajax-success";
    }

    public String input(){
        request.put("departments",departmentService.getAll());
        return INPUT;
    }

    public String save(){
        model.setCreateTime(new Date());
        employeeService.saveOrUpdate(model);
        return SUCCESS;
    }

    private Map<String,Object>request;

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    @Override
    public EmployeeEntity getModel() {
        return model;
    }
    private EmployeeEntity model;

    @Override
    public void prepare() throws Exception {

    }
    public void prepareSave(){
        model = new EmployeeEntity();
    }

    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String validateLastName() throws UnsupportedEncodingException {
        System.out.println("执行validateLastName");
        if (employeeService.lastNameIsValid(lastName)){
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        }else{
            inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
        }
        return "ajax-success";
    }
}
