# 什么是Quartz？
一个定时任务调度框架，简单易用，功能强大可以使实现定时任务的。
在项目开发过程当中，某些定时任务，可能在运行一段时间之后，就不需要了，或者需要修改下定时任务的执行时间等等。
需要在代码当中进行修改然后重新打包发布，很麻烦。使用Quartz来实现的话不需要重新修改代码而达到要求。
# 实现定时调度任务的动态暂停，修改，启动，单次执行等操作
## 第一步 创建一个定时任务相关实体类用于保存定时任务相关信息到数据库当中
QuartzBean
## 第二步 创建定时任务暂停，修改，启动，单次启动工具类
QuartzUtils 
## 第三步 创建一个定时任务和相关测试类。
Job1、QuartzController
## 第四步 总结

1 springboot2.0后默认添加了quartz的依赖，可以少些很多配置信息，只需要写好自己的任务类（需要实现job类）然后通过调度器scheduler添加任务就可以了。

2 通过@Bean注解简单创建定时任务的时候，直接写任务类的class就可以，但是通过scheduler的时候需要写绝对名称。

3 在quartz任务暂停之后再次启动时，会立即执行一次，在更新之后也会立即执行一次。

4 在springboot当中默认quartz线程池大小为10。

5 在启动项目初始化时需要将项目的定时任务也进行初始化。这样比较方便不用依次进行启动