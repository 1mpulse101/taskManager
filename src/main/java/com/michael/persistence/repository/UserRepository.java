package com.michael.persistence.repository;

import com.michael.persistence.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
    User findByUserId(Integer userId);

    List<User> findByLastName(String lastName);

    @Query(value = "SELECT * FROM user WHERE first_name = :firstName", nativeQuery = true)
    List<User> retrieveByFirstName(@Param("firstName") String firstName);
}
