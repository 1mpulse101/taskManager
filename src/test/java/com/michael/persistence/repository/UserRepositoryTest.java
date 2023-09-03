package com.michael.persistence.repository;

import com.michael.persistence.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User user1 = new User(1001, "Harry", "Potter", "Harry.Potter@gmail.com");
        User user2 = new User(1002, "John", "Doe", "John.Doe@gmail.com");
        userRepository.saveAll(Arrays.asList(user1, user2));
    }

    @AfterEach
    public void cleanup() {
        userRepository.deleteById(1001);
        userRepository.deleteById(1002);
    }

    @Test
    public void test_findByUserId() {
        User user = userRepository.findByUserId(1001);
        Assert.assertEquals("Harry", user.getFirstName());
    }

    @Test
    public void test_findByFirstName() {
        List<User> users = userRepository.retrieveByFirstName("Harry");
        Assert.assertEquals("Harry", users.get(0).getFirstName());
    }

    @Test
    public void test_findByLastName() {
        List<User> users = userRepository.findByLastName("Potter");
        Assert.assertEquals("Harry", users.get(0).getFirstName());
    }
}
