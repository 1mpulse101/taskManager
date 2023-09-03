package com.michael.persistence.controller;

import com.michael.persistence.bean.UserTask;
import com.michael.persistence.entities.Task;
import com.michael.persistence.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getTaskList") // http://localhost:8080/getTaskList?userId=1001
    public UserTask getTaskList(@RequestParam(value = "userId") int userId) {
        return taskService.getTaskByUserId(userId);
    }

    @GetMapping("/getTasks/{userId}") // http://localhost:8080/getTasks/1001
    public UserTask getTasks(@PathVariable int userId) {
        return taskService.getTaskByUserId(userId);
    }

    @PostMapping(value = "/newTask", consumes = "application/json", produces = "application/json")
    public void newTask(@RequestBody Task task) {
        taskService.newTask(task);
    }
}
