package com.yzp.spring.springsamples.activity;

import com.yzp.spring.springsamples.activity.service.MyService;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivityApplication {

    public static void main(String[] args) {

        SpringApplication.run(ActivityApplication.class,args);
        //myProcess_1
    }

    /**
     * 测试activiti    对于  CommandLineRunner 用法具体百度
     * @param repositoryService
     * @param runtimeService
     * @param taskService
     * @return
     */
    /*@Bean
    public CommandLineRunner innit(final RepositoryService repositoryService,
                                   final RuntimeService runtimeService,
                                   final TaskService taskService)
    {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                System.out.println("流程定义数量: "
                                +repositoryService.createProcessDefinitionQuery().count());

                System.out.println("任务定义数量: "+taskService.createTaskQuery().count());


                // myProcess_1 是定义的流程ID 可以在 BPM 文件中看到
                runtimeService.startProcessInstanceByKey("myProcess_1");

                System.out.println("流程启动后的任务数量: "+
                        taskService.createTaskQuery().count());
            }
        };
    }*/

    /**
     *  项目启动 创建 person
     * @param myService
     * @return
     */
    @Bean
    public CommandLineRunner init(final MyService myService)
    {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                myService.createDemoUsers();
            }
        };
    }
}
