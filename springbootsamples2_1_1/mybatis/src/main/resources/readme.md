## mybatis配置说明
### 导入jar包
- 前提 pom文件 引入 了 ‘数据库访问相关’jar包
- pom文件引入 ‘mybatis相关’jar包
### 建立mybatis配置文件
- resources 下新建mybatis目录用于放文件
- resources 下新建mybatis/mybatis-config.xml，用于设置mybatis的配置信息
- resources 下新建mybatis/**/mapper/**.xml，用于配置sql语句的xml文件
- 配置内容参考文件中的注解
### 将mybatis配置文件配置到springboot
- 详见application.yml文件中mybatis的配置
### 数据库连接池配置
com.yzp.spring.springsamples2_1_1.mybatis.config.DruidConfig
### 代码测试
- 注意写mapper接口时，要让spring能够识别到要加@Mapper注解或者统一在springboot启动类中
加注解MappperScan("mapperr接口的具体路径")避免每次都要加@Mapper注解