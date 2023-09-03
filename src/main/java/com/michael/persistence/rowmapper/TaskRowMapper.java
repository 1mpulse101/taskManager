package com.michael.persistence.rowmapper;

import com.michael.persistence.entities.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setTaskId(rs.getInt("task_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setTaskDesc(rs.getString("task_description"));
        task.setStatus(rs.getString("status"));
        task.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        task.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
        task.setCreationDate(rs.getTimestamp("end_date").toLocalDateTime().toLocalDate());
        return task;
    }
}
