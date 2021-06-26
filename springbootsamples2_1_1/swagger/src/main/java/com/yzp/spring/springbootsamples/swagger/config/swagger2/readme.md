### swagger介绍
- 目前大多数项目都是前后端分离，从前前端与后端对接接口时需要有特定的接口文档，而且后端的接口可能随时变化，造成维护文档变的不方便。
- swagger就是为了解决这项问题，作用如下：
    - 接口文档在线自动生成
    - 测试接口不需要在用POSTMAN或者其它方式来测试，直接在线点击即可测试接口
### springboot配置swagger步骤
- 1导入相关jar包
    - 详见pom文件中'swagger相关包'
- 2添加swagger的配置文件
    - 详见当前目录的Swagger2Config,其中有配置说明
- 3在接口类和方法上添加参数说明
    - 详见yzpDaily/swaggerModule/controller/SwaggerTestController,其中有配置说明
- 4访问地址 http://localhost:8082/swagger-ui.html    