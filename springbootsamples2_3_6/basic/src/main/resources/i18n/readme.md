## 说明
### 步骤一：添加中英文信息
- Resource Bundle 'loginModule'目录
    - 目录是自动生成的，
    只要添加了**.properties、**_en_US.properties、**_zh_CH.properties文件，
    springboot就会自动加上
    - **.properties文件存放默认的信息、
     **_en_US.properties存放英文的信息，en代表英文语言代码，US代表美国国家代码、
     **_zh_CH.properties存放中文的信息，同理zh代表中文语言代码，CH代表中国国家代码。
### 步骤二：设置中英文转换配置
- 要使浏览器页面可以自定义显示我们设置的中英文信息，就需要对springMVC进行一些配置，
  让它可以取到对应的值，具体配置内容详见 com.yzp.spring.springbootsamples.basic.usage.mvc.MySpringMvcConfig.localeResolver
### 步骤三：页面显示
- 参照：templates/commonPage/i18nPage/loginModule/loginIn.html