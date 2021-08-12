package com.yzp.spring.springbootsamples.activity.service;


import com.yzp.spring.springbootsamples.activity.model.Person;
import com.yzp.spring.springbootsamples.activity.mapper.PersonRepository;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    /**
     * 根据流程实例启动一个流程
     */
    @Transactional
    public void startProcess(String assignee)
    {
        Person person = personRepository.findByUsername(assignee);

        Map<String, Object> variables = new HashMap<>();
        variables.put("person",person);


        //runtimeService.startProcessInstanceByKey("myProcess_1");

        runtimeService.startProcessInstanceByKey("myProcess_1",variables);
    }

    /**
     * 查看任务处理人当前的任务信息
     * @param assigneeId
     * @return
     */
    public List<Task> getTasks(String assigneeId)
    {

        return taskService.createTaskQuery().taskAssignee(assigneeId).list();
    }

    /**
     *  如果当前没有数据，就添加两条记录
     */
    public void createDemoUsers(){
        if(personRepository.findAll().size() == 0)
        {
            personRepository.save(new Person("YAOZHENPENG","YAO","ZHENPENG",new Date()));
            personRepository.save(new Person("YAOZHENPENG2","YAO2","ZHENPENG2",new Date()));
        }
    }
}
