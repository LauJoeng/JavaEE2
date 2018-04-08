1. 加入spring

1).加入jar包

2).配置web.xml文件


3).加入Spring的配置文件

2. 加入hibernate

1).同时建立持久化类，和其对应的.hb.xml文件，生成对应数据表

2).spring整合Hibernate

3).步骤

①加入jar包

②.在类路径下加入hibernate.cfg.xml文件，在其中配置hibernate的基本属性

③.建立持久化类，和其对应的.hbm.xml文件

④.和Spring进行整合

i.加入c3p0和数据库驱动

ii.在Spring的配置文件中配置：数据源，sessionFactory，声明式事务

i. 加入c3p0和mysql驱动

ii.在spring的配置文件中配置:数据源,SessionFactory,声明式事务

⑥启动项目，会看待生成对应的数据表

3. 加入Struts2

1).加入jar包:若有重复jar包，则需要删除

2).在web.xml文件中配置Struts2的Filter

3).加入Struts2的配置文件

3).整合Spring

①.加入Struts2的Spring插件jar包

②.在Spring的配置文件中正常配置Action,注意Action的scope属性为prototype

③.在Struts2的配置文件中配置Action时，class属性指向IOC容器中的id


4. 完成功能

1).获取所有员工的信息，若在dao中只查询Employee的信息，而且Employee和Department还是使用懒加载，页面上还需要显示员工信息，此时会出现懒加载异常,代理对象不能被初始化

解决:
①打开懒加载:不推荐使用
②获取Employee时使用迫左外连接同时初始化其关联的Department对象
③使用openSessionInViewFilter

2).删除员工信息：

正常删除，返回值是redirect类型
确定要删除吗? 的提示使用jQuery完成
Ajax

3).添加员工：

①显示表单页面：需要先查寻所有的部门信息

②使用Struts2的ModelDriven和Preparable拦截器

③时间是一个字符串，需要转为一个Date类型对象