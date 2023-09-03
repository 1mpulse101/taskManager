package com.michael.persistence.service;

import com.michael.persistence.bean.UserTask;
import com.michael.persistence.entities.User;
import com.michael.persistence.entities.Task;
import com.michael.persistence.jdbc.UserTaskDao;
import com.michael.persistence.repository.TaskRepository;
import com.michael.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserTaskDao userTaskDao;

    public UserTask getTaskByUserId(Integer userId) {
        User user = userRepository.findByUserId(userId);
        //List<Task> tasks = taskRepository.findByUserId(userId);
        List<Task> tasks = taskRepository.findByUser(user);
        return convert(user, tasks);
    }

    public UserTask retrieveTaskByUserId(Integer userId) {
        User user = userTaskDao.getUserByUserId(userId);
        List<Task> tasks = userTaskDao.getTaskByUserId(userId);
        return convert(user, tasks);
    }

    private UserTask convert(User user, List<Task> tasks) {
        UserTask userTask = new UserTask(user.getFirstName(), user.getLastName(), user.getEmailAddress());
        List<UserTask.Task> uTasks = new ArrayList<>();
        userTask.setTasks(uTasks);
        tasks.stream().forEach(task -> {
            UserTask.Task uTask = new UserTask.Task(task.getTaskName(), task.getTaskDesc(), task.getStatus(), task.getStartDate(), task.getEndDate());
            uTasks.add(uTask);
        });
        return userTask;
    }

    public void newTask(Task task) {
        taskRepository.save(task);
    }
}
