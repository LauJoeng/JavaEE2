package com.yang.springmvc.handler;

import com.yang.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = {"user"},types = {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";

    /**
     * 使用params和headers来更加精确的映射请求，params和headers可以使用表达式
     * @return
     */
    @RequestMapping(value = "testParamsAndHeaders",params = {"username","age!=10"}
    ,headers = {"Accept-Language=zh-CN,zh;q=0.8"})
    public String testParamsAndHeaders(){
        System.out.println("testParamsAndHeaders");
        return SUCCESS;
    }


    /**
     * 使用method属性来指定请求方式
     * @return
     */
    @RequestMapping(value = "/testMethod",method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("testMethod");
        return SUCCESS;
    }


    /**
     * 1.@RequestMapping除了修饰方法外，还可以修饰类
     * 2.类定义处:提供初步的请求映射信息。相对于WEB应用的根目录
     * 方法处:提供进一步的细分信息。相对于类定义处的URL。若定义处未标注@RequestMapping，则方法处标记的URL相对于WEB应用的根目录
     * @return
     */
    @RequestMapping("/requestMapping")
    public String requestMapping(){
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath(){
        System.out.println("testAntPath");
        return SUCCESS;
    }

    /**
     * @PathVariable 可以来映射URL中的占位符到目标方法的参数中
     * @param id
     * @return
     */
    @RequestMapping("/testPathVariable/{id}")
    public String  testPathVariable(@PathVariable(value = "id")Integer id){
        System.out.println(id);
        return SUCCESS;
    }


    /**
     * Rest风格的URL
     * 以CRUD为例:
     * 新增:/order  POST
     * 修改:/order/1 PUT
     * 获取：/order/1 GET
     * 删除：/order/1 DELETE
     *
     * 如何发送PUT 请求和 DELETE 请求:
     * 1.需要配置HiddenHttpMethodFilter
     * 2.需要定义POST请求
     * 3.需要在发送POST请求时需要携带一个name="method"的隐藏域，value=delete或put即可
     *
     * 在SpringMVC的目标方法中如何得到id：使用@PathVariable
     * @param id
     * @return
     */
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.GET)
    public String testRest(@PathVariable Integer id){
        System.out.println("testRest GET:  "+id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest}",method = RequestMethod.POST)
    public String testRest(){
        System.out.println("testRest POST:");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id){
        System.out.println("testRest DELETE:  "+id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id){
        System.out.println("testRest PUT:  "+id);
        return SUCCESS;
    }


    /**
     * @ReqeuestParam 来映射请求参数
     * value 值即为请求参数的参数名
     * required  该参数是否必须
     * defaultValue  参数默认值
     */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username",required = false)String username,
                                   @RequestParam(value = "age",defaultValue = "0")Integer age){
        System.out.println("username = "+username+",age = "+age);
        return SUCCESS;
    }

    /**
     * 用法：同RequestParam
     * 映射请求头信息
     * @param al
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language")String al){
        System.out.println("testRequestHeader"+"Accept-Language:" + al);
        return SUCCESS;
    }

    /*
     * @CookieValue:映射一个Cookie值，属性同@RequestParam
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID")String sessionId){
        System.out.println("testCookieVaLue:sessionId"+sessionId);
        return SUCCESS;
    }

    /**
     * 测试自动匹配参数
     * @param user
     * @return
     */
    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println("testPojo:"+user);
        return SUCCESS;
    }

    /**
     * 可以使用Servlet原生的API作为目标方法的参数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request + " : "+ response);
        return SUCCESS;
    }

    /**
     * 目标方法的返回值可以是ModelAndView类型，
     * 其中可以包含视图和模型信息
     * SpringMVC会把ModelAndView的model中数据放入到request域对象中
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);

        //添加模型数据到ModelAndView中
        modelAndView.addObject("time",new Date());
        return modelAndView;
    }


    /**
     * 目标方法可以添加Map类型(实际上也可以是Model类型或ModelMap类型)的参数。
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object>map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("tom","jerry","lucy","Mike"));
        return SUCCESS;
    }

    /**
     * @SessionAtrributes 除了可以通过属性名指定需要放到会话中的属性之外（实际上使用的是value属性值），
     * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是types属性值）
     *
     * 注意:该注解只能放在类的上面，而不能放在方法上面
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object>map){
        User user = new User("Tom","123456","tom@123.com",13);
        map.put("user",user);
        map.put("school","peking");
        return SUCCESS;
    }




    /**
     * 1.由@ModelAtrribute 标记的方法，会在每个目标方法执行之前被SpringMVC调用
     * 2.这个注解也可以来修饰目标方法的POJO类型的入参，其中value属性值会有如下作用
     * 1).SpringMVC会使用value属性值在implicitModel中查找对象，若存在则会直接传入到目标方法的入参中
     * 2).SpringMVC会以value为key，将POJO类型的对象为value，存入到request中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false)Integer id,Map<String,Object> map){
        System.out.println("ModelAttribute Method");
        if (id != null){
            //模拟从数据库中获取对象
            User user = new User(1,"Tom","123456","tom@123.com",12);
            System.out.println("从数据库中获取一个对象: "+user);
            map.put("user",user);
        }
    }
    /**
     * 运行流程:
     * 1.执行@ModelAttribute注解修饰的方法，从数据库中取出对象，把对象放入了Map中，键为：user
     * 2.SpringMVC从Map中取出对象，并把表单的请求参数赋给User兑现对应属性
     * 3.SpringMVC把上述对象传入目标方法的参数
     *
     * 注意：在@ModelAttribute修饰的方法中，放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致！
     *
     * SpringMVC确定目标方法POJO类型入参的过程
     * 1.确定一个key
     * 1).若目标方法的POJO类型的参数没有使用@ModelAttributes作为修饰，则key为POJO类名第一个字母小写的字符串
     * 2）.若使用了@ModelAttributes来修饰，则key为@ModelAttributes注解的value值
     * 2.若implicitModel中查找key对应的对象，若存在，则作为入参传入
     * 1）若在@ModelAttribute标记的方法中在Map中保存过，且key和1确定的key一致，则会获取到
     * 3.若implicitModel中不存在key对应的对象，则检查当前Handler是否使用@SessionAttributes修饰
     * 若使用了该注解，且@SessionAttributes注解的value属性值中包含了key，则会从HttpSession中来获取key所对应的value值，若存在直接传入目标方法入参中，不存在，抛出异常
     * 4.若Handler没有标识@SessionAttributes注解或@SessionAttributes注解中value值不包含key，则会通过反射来创建POJO类型参数传入为目标方法的入参
     * 5.SpringMVC会把key和value保存到implicitModel中，进而保存到request中
     *
     *
     * 源码分析：
     * 1.调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中Map中的数据放在了implicitModel中
     * 2.解析请求处理器目标参数，实际该目标参数来自于WebDataBinder对象的target属性
     * 1).创建WebDataBinder对象:
     * ①确定objectName属性：若传入的attrName属性值为""，则object为类名第一个字母小写的字符串
     * 注意:attrName，若目标方法的POJO属性了@ModelAttribute来修饰，则attrName值即为@ModelAttribute的value值
     *
     * ②确定target属性:
     *  > 在implicitModel中查找对应的属性值。若存在，ok
     *  > *若不存在:则验证当前handler是否使用了@SessionAtrributes修饰，若使用了，则尝试从session中获取attrName所对应的属性值
     * 若session中没有对应的属性值，则抛出异常
     * > 若Handler没有使用@SessionAttributes进行修饰，或@SessionAttributes中没有使用value值指定的key和attrName相匹配，
     * 则通过反射创建POJO对象
     * 2)   SpringMVC把表单的请求参数赋值给了WebDataBinder的target属性。
     * 3) *SpringMVC会把WebDataBinder的attrName和target给到implicitModel，进而传到request域对象中
     * 4）   把WebDataBinder的target作为参数传递给目标方法的入参
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user){
        System.out.println("修改:"+user);
        return SUCCESS;
    }


    /**
     *
     * 请求处理方法执行完成后，最终返回一个ModelView对象。对于那些返回String，View或ModelMap等类型的处理方法，内部也会
     * 把他们装配为一个ModelAndView对象，它包含了逻辑名和模型对象的视图。SpringMVC借助视图解析器(ViewResolver)得到最终
     * 的视图可以是jsp，也可能是Exel、JFreeChart等各种表现形式的视图
     */
    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }
}
