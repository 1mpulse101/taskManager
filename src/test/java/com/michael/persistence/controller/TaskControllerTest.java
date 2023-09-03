package com.michael.persistence.controller;


import com.michael.persistence.bean.UserTask;
import com.michael.persistence.entities.Task;
import com.michael.persistence.entities.User;
import com.michael.persistence.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testGetTaskList() {
        UserTask response = template.getForObject("/getTaskList?userId=99999", UserTask.class);
        assertEquals("Harry", response.getFirstName());
        assertEquals(1, response.getTasks().size());
    }

    @Test
    public void testGetTasks() {
        UserTask response = template.getForObject("/getTasks/99999", UserTask.class);
        assertEquals("Harry", response.getFirstName());
        assertEquals(1, response.getTasks().size());
    }

    @Test
    public void testNewTask() {
        User user = new User(99999, "Harry", "Potter", "Harry.Potter@gmail.com");
        Task task = new Task();
        task.setTaskId(10002);
        task.setUser(user);
        task.setTaskName("Assignment 9");
        task.setTaskDesc("ECON361 Assignment 9");
        task.setStatus("New");
        task.setCreationDate(LocalDate.now());
        task.setStartDate(LocalDateTime.of(2023, Month.SEPTEMBER, 12, 8, 0));
        task.setEndDate(LocalDateTime.of(2023, Month.SEPTEMBER, 30, 11, 0));

        template.postForObject("/newTask", task, String.class);

        UserTask response = template.getForObject("/getTasks/99999", UserTask.class);
        assertEquals("Harry", response.getFirstName());
        assertEquals(2, response.getTasks().size());

        taskRepository.delete(task);
    }
}
