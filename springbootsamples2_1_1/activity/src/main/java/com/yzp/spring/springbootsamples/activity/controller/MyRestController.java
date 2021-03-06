package com.yzp.spring.springbootsamples.activity.controller;

import com.yzp.spring.springbootsamples.activity.service.MyService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private MyService myService;


    /**
     * 启动流程
     */
    @RequestMapping(value = "/process",method = RequestMethod.POST)
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation){

        //myService.startProcess();
        myService.startProcess(startProcessRepresentation.getAssignee());

    }

    /**
     * 根据处理人查看任务信息
     *
     * @param assigneeId
     * @return
     */
    @RequestMapping(value = "/tasks",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assigneeId){

        List<Task> tasks = myService.getTasks(assigneeId);

        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();

        for(Task task: tasks)
        {
            dtos.add(new TaskRepresentation(task.getId(),task.getName()));
        }
        return dtos;
    }

    static class StartProcessRepresentation{

        private String assignee;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }

    static class TaskRepresentation{

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

