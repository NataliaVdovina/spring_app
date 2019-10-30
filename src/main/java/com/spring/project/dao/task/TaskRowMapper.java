package com.spring.project.dao.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("task_id");
        Long userId = resultSet.getLong("user_id");
        String taskName = resultSet.getString("task_name");
        String status = resultSet.getString("status");
        String taskPriority = resultSet.getString("taskPriority");

        return Task.builder()
                .taskId(id)
                .userId(userId)
                .taskName(taskName)
                .taskStatus(TaskStatus.valueOf(status))
                .taskPriority(TaskPriority.valueOf(taskPriority))
                .build();

    }
}
