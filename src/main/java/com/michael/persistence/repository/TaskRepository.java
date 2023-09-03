package com.michael.persistence.repository;

import com.michael.persistence.entities.Task;
import com.michael.persistence.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Integer> {
    //List<Task> findByUserId(int userID);
    List<Task> findByUser(User user);
}
