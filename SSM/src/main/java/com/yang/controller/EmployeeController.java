package com.yang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.bean.DepartmentExample;
import com.yang.bean.Employee;
import com.yang.bean.Msg;
import com.yang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //查询员工数据(分页查询)
//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pageNum,Model model){
        //引入PageHelper分页插件
        //在查询之前只需要调用,传入页码，以及每页大小
        PageHelper.startPage(pageNum,5);
        //startPage后面紧跟的这个查询就是分页查询
        List<Employee>emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需将pageinfo交给页面就行了，里面封装了查询出来的数据,可以传入连续显示页数
        PageInfo<Employee> pageInfo = new PageInfo<>(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }

    /**
     * 需要jackson包
     * @param pageNum
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        //startPage后面紧跟的这个查询就是分页查询
        List<Employee>emps = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(emps,5);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 支持JSR303校验
     * 导入Hibernate-Validator包
     * @param employee
     * @return
     */
    @RequestMapping(value="/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            //在模态框中显示后端校验失败的信息
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String,Object>map = new HashMap<>();
            for(FieldError error:fieldErrors){
                System.out.println("错误的字段名:"+error.getField());
                System.out.println("错误的信息"+error.getDefaultMessage());
                map.put(error.getField(),error.getDefaultMessage());
            }
            return Msg.failed().add("errorFields",map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @RequestMapping("/checkuser")
    @ResponseBody
    public Msg checkuser(@RequestParam("empName") String empName){
        //先判断用户名是否是合法的表达式
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)";
        if(!empName.matches(regex)){
            return Msg.failed().add("val_msg","用户名必须是6-16位数字和字母的组合或2-5位中文");
        }
        //数据库用户名校验
        boolean b = employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else{
            return Msg.failed().add("val_msg","用户名不可用");
        }
    }

}
