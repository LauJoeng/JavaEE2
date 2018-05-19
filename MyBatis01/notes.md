**单个参数**

单个参数:mybatis不会做特殊处理
<br/>#{参数名}，其实随便写什么都无所谓，都能取到参数

多个参数：mybatis会做特殊处理<br/>
    多个参数会被封装成一个map，<br/>
    key:param1....paramN<br/>
    value：传入的参数值<br/>
    
命名参数:明确指出封装参数时map的key:在mapper接口中对方法参数直接使用@Param注解

如果多个参数正好是业务逻辑的数据模型，我们就可以直接传入pojo:#{属性名}去处pojo的属性值

如果多个参数不是业务逻辑模型中的数据，没有对应的pojo，则可以传入,map#{key}去出map中对应的值

如果多个参数不是业务模型中的数据，但是经常使用，推荐编写一个TO(transfer object)数据传输对象

Page{
    int index;
    int size;
}

参数处理方法源码如下

```
public Object getNamedParams(Object[] args) {
        int paramCount = this.names.size();
        if (args != null && paramCount != 0) {
            if (!this.hasParamAnnotation && paramCount == 1) {
                return args[(Integer)this.names.firstKey()];
            } else {
                Map<String, Object> param = new ParamMap();
                int i = 0;

                for(Iterator var5 = this.names.entrySet().iterator(); var5.hasNext(); ++i) {
                    Entry<Integer, String> entry = (Entry)var5.next();
                    param.put(entry.getValue(), args[(Integer)entry.getKey()]);
                    String genericParamName = "param" + String.valueOf(i + 1);
                    if (!this.names.containsValue(genericParamName)) {
                        param.put(genericParamName, args[(Integer)entry.getKey()]);
                    }
                }

                return param;
            }
        } else {
            return null;
        }
    }
```

参数值的获取
<br/>#{} 可以取出map中的值或pojo对象的属性值
<br/>${} 与上面相同
<br/>区别:#{}是以预编译的方式，将参数设置到sql语句中:PrepareStatement，而${}将取出的值直接拼接在sql语句中，大多数情况使用第一种，当排序，或分组参数时会用第二种