package com.yang.springmvc.crud.handler;

import com.sun.org.apache.regexp.internal.RE;
import com.yang.springmvc.crud.dao.DepartmentDao;
import com.yang.springmvc.crud.dao.EmployeeDao;
import com.yang.springmvc.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String,Object> map){
        map.put("employees",employeeDao.getAll());
        return "list";
    }

    @RequestMapping(value = "emp",method = RequestMethod.GET)
    public String input(Map<String,Object>map){
        System.out.println("input:get");
        map.put("employee",new Employee());
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }



    @RequestMapping(value = "emp",method = RequestMethod.POST)
    public String save(@Valid Employee employee, Errors bindingResult, Map<String,Object>map){
        System.out.println("save:  "+employee);
        if (bindingResult.getErrorCount()>0){
            System.out.println("出错了！");
            for (FieldError error :bindingResult.getFieldErrors()){
                System.out.println(error.getField() + " : " + error.getDefaultMessage());
            }
            //若验证出错，则转向定制页面
            map.put("departments",departmentDao.getDepartments());
            return "input";
        }
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete");
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id,Map<String,Object>map){
        System.out.println("input:get(id)");
        map.put("employee",employeeDao.getEmployee(id));
        map.put("departments",departmentDao.getDepartments());
        return "input";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false)Integer id,Map<String,Object>map){
        if (id!=null){
            map.put("employee",employeeDao.getEmployee(id));
            System.out.println(employeeDao.getEmployee(id).toString());
        }
    }

    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
        System.out.println(employee.toString());
        System.out.println("update");
        employeeDao.save(employee);

        return "redirect:/emps";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.setDisallowedFields("lastName");
//    }
}
