package com.michael.persistence.repository;

import com.michael.persistence.entities.Task;
import com.michael.persistence.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setup() {
        User user1 = new User(1001, "Harry", "Potter", "Harry.Potter@gmail.com");
        User user2 = new User(1002, "John", "Doe", "John.Doe@gmail.com");
        userRepository.saveAll(Arrays.asList(user1, user2));

        Task task = new Task();
        task.setTaskId(1001);
        //task.setUserId(1001);
        task.setUser(user1);
        task.setTaskName("Assignment 2");
        task.setTaskDesc("ECON361 Assignment 2");
        task.setStatus("New");
        task.setCreationDate(LocalDate.now());
        task.setStartDate(LocalDateTime.of(2023, Month.SEPTEMBER, 8, 8, 0));
        task.setEndDate(LocalDateTime.of(2023, Month.SEPTEMBER, 28, 11, 0));
        taskRepository.save(task);
    }

    @AfterEach
    public void cleanup() {
        userRepository.deleteById(1001);
        userRepository.deleteById(1002);
    }

    @Test
    public void test_findByUserIdWithTasks() {
        User user = userRepository.findByUserId(1001);
        Assertions.assertEquals("Harry", user.getFirstName());

        List<Task> tasks = taskRepository.findByUser(user);
        Assertions.assertEquals(1001, tasks.get(0).getTaskId());
    }
}
