package com.michael.persistence.jdbc;

import com.michael.persistence.entities.Task;
import com.michael.persistence.entities.User;
import com.michael.persistence.rowmapper.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserByUserId(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                            new User(rs.getInt("user_id"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getString("email_address")),
                    new Object[]{userId});
        } catch (EmptyResultDataAccessException e) {
        }
        return user;
    }

    public List<Task> getTaskByUserId(int userId) {
        String sql = "SELECT * FROM task WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Task(rs.getString("task_name"),
                                rs.getString("task_description"),
                                rs.getString("status"),
                                rs.getTimestamp("start_date").toLocalDateTime(),
                                rs.getTimestamp("end_date").toLocalDateTime()),
                new Object[]{userId});
    }

    public List<Task> retrieveTaskByUserId(int userId) {
        String sql = "SELECT * FROM task WHERE user_id = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), new Object[]{userId});
    }
}
