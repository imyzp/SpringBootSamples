## 说明

classpath即类路径 /src/main/

### springboot启动配置文件
- springboot启动时默认会扫描以下目录寻找配置文件
    - /config/配置文件名称，项目根目录的config下配置文件拥有最高级别，高级别的配置会覆盖低级别的配置
    - /配置文件名称，项目根目录下配置文件拥有第二高级别
    - classpath/resources/config/配置文件,项目类路径的config下配置文件拥有第三级别
    - classpath/resources/配置文件，项目类路径下的配置文件拥有最低级别
### 静态资源目录
    - 当接受到/**请求访问资源时，会默认映射到下面4个类路径下的静态资源目录中查找
        classpath:/META-INF/resources/
        classpath:/resources/
        classpath:/static/
        classpath:/public/
### 模板引擎
- 模板引擎目录
    - classpath:/resources/用于存放支持模板引擎的页面
    - 模板页面需要视图解析器，必须经过服务器后才能访问

    springboot官方不推荐使用jsp,因为内嵌tomcat、jetty容器不支持以jar方式形式运行jsp
    springboot中提供了大量模板引擎，包含Freemarker、Thymeleaf等。
    springboot官方推荐Thymeleaf，因为Thymeleaf提供了完美的SpringMVC支持
    只要将HTML放在classpath:/templates目录下模板引擎就能自动渲染
    

### 欢迎页

- 项目启动时，会从默认的静态资源目录和项目根目录查找index.html

### 系统页面标签图标

- 项目启动时，会从默认的静态资源目录和项目根目录查找favicon.ico，如果存在则设置为应用图标

### 国际化配置

i18n
### springboot的页面错误处理方式
- 当用户请求的连接出现404或者500时，
    会寻找对应错误状态码.html页面展示。
    页面存放在error目录下，
    springboot会从模板引擎目录templates、静态资源目录寻找error目录，
    如果都找不到，就响应springboot默认的错误页面，
    xx代码模糊匹配对应类型的所有错误

### 定时任务

schedule

### 异步任务

async

### 自定义注解实现aop

aop

### spring 自带的 controller 写法

api

### spring 接口参数校验方法

validation

### 系统配置参数

configparam