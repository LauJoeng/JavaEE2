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

i. 加入c3p0和mysql驱动
ii.在spring的配置文件中配置:数据源,SessionFactory,声明式事务
⑥启动项目，会看待生成对应的数据表

3. 加入Struts2

4. 完成功能