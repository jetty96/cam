package com.example.workflow;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class TaskStatusChecker {

    @Autowired
    private HistoryService historyService;

    @Scheduled(fixedDelay = 10000)
    public void checkTaskStatus() {
        List<HistoricTaskInstance> newTasks = historyService.createHistoricTaskInstanceQuery()
                .unfinished()
                .list();

        for (HistoricTaskInstance task : newTasks) {
            System.out.println("New Task Created - ID: " + task.getId() +
                    ", Name: " + task.getName() +
                    ", Assignee: " + task.getAssignee() +
                    ", Start Time: " + task.getStartTime());
        }

        List<HistoricTaskInstance> completedTasks = historyService.createHistoricTaskInstanceQuery()
                .finished()
                .list();

        for (HistoricTaskInstance task : completedTasks) {
            System.out.println("Task Completed - ID: " + task.getId() +
                    ", Name: " + task.getName() +
                    ", Assignee: " + task.getAssignee() +
                    ", Start Time: " + task.getStartTime() +
                    ", End Time: " + task.getEndTime());
        }

        //////// Using Task Service ////////////
//        List<Task> tasks = taskService.createTaskQuery().list();
//
//        // Print task status
//        for (Task task : tasks) {
//            String taskId = task.getId();
//            if (!processedTaskIds.contains(taskId)) {
//                System.out.println("Task ID: " + taskId);
//                System.out.println("Task Name: " + task.getName());
//                System.out.println("Task Assignee: " + task.getAssignee());
//                System.out.println("Task Description: " + task.getDescription());
//                System.out.println("Task Status: " + (task.getEndTime() != null ? "Completed" : "Pending"));
//                System.out.println("==============================");
//
//                // Add the task ID to the set of processed task IDs
//                processedTaskIds.add(taskId);
//            }
//        }
    }
}
