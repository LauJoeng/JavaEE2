依赖的范围

-compile
对主程序，测试程序有效，参与打包参与部署，典型例子为spring-core

- test
对主程序无效，对测试程序无效，不参与打包，不参与部署，典型例子为Junit

- provided
对主程序，测试程序有效，不参与打包，不参与部署，典型例子如servlet-api.jar