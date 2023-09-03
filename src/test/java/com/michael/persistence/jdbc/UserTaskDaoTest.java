package com.michael.persistence.jdbc;

import com.michael.persistence.entities.Task;
import com.michael.persistence.entities.User;
import com.michael.persistence.repository.TaskRepository;
import com.michael.persistence.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
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
public class UserTaskDaoTest {
    @Autowired
    private UserTaskDao userTaskDao;

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
    public void test_getUserByUserID() {
        User user = userTaskDao.getUserByUserId(1001);
        Assert.assertEquals("Harry", user.getFirstName());
    }

    @Test
    public void test_getTaskByUserId() {
        List<Task> tasks = userTaskDao.getTaskByUserId(1001);
        Assert.assertEquals("Assignment 2", tasks.get(0).getTaskName());
    }

    @Test
    public void test_retrieveTaskByUserId() {
        List<Task> tasks = userTaskDao.retrieveTaskByUserId(1001);
        Assert.assertEquals("Assignment 2", tasks.get(0).getTaskName());
    }
}
