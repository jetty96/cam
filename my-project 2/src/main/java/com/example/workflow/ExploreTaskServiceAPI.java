package com.example.workflow;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@EnableScheduling
public class ExploreTaskServiceAPI {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;


    //print user tasks every 15 seconds
    @Scheduled(fixedRate = 15000)
    public void printUserTasks() {
        int numUsers = 5;
        for(int i = 0; i < numUsers; i++) {
            String userName = "UserName" + i;
            List<Task> tasks = taskService.createTaskQuery().taskAssignee(userName).list();
            if (!tasks.isEmpty()) {
                System.out.println("Tasks assigned to user " + userName + ":");
                for (Task task : tasks) {
                    System.out.println("Task Id: " + task.getId() + ", Name: " + task.getName());
                }
            } else {
                System.out.println("No tasks assigned to user " + userName);
            }
        }
    }
}
