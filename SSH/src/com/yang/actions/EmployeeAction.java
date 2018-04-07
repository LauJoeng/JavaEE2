package com.yang.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.yang.service.EmployeeService;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

public class EmployeeAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 1L;

    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String list(){
        request.put("employees",employeeService.getAll());
        return "list";
    }

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public String delete(){
        employeeService.delete(id);
        return SUCCESS;
    }

    private Map<String,Object>request;

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }
}
