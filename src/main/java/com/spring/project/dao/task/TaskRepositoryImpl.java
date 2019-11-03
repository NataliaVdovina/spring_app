package com.spring.project.dao.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final String INSERT = "insert into tasks (user_id, task_name, status, task_priority) values(?,?,?,?)";
    private final String DELETE_BY_ID = "delete from tasks where task_id = ?";
    private final String FIND_ALL_TASKS_BY_USER = "select * from tasks where users_id = ?";
    private final String SET_STATUS = "update users set status=? where task_id=?";
    private final String SET_TASK_PRIORITY = "update users set taskPriority=? where task_id=?";
    private final String GET_TASK_COUNT_BY_USER_ID = "select task_id, count(task_id) from tasks GROUP BY task_id";

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createTask(Long userId, String taskName) {
        jdbcTemplate.update(INSERT, userId, taskName, TaskStatus.OPEN, TaskPriority.LOW);
    }

    @Override
    public void deleteTask(Long taskId) {
        jdbcTemplate.update(DELETE_BY_ID, taskId);
    }

    @Override
    public List<Task> findAllTasksByUser(Long userId) {
        return jdbcTemplate.query(FIND_ALL_TASKS_BY_USER, new TaskRowMapper(), userId);
    }

    @Override
    public void setStatus(Long taskId, TaskStatus taskStatus) {
        jdbcTemplate.update(SET_STATUS, taskStatus, taskId);
    }

    @Override
    public void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority) {
        jdbcTemplate.update(SET_TASK_PRIORITY, taskPriority, taskId);
    }

    @Override
    public Long getTaskCountByUserId(Long userId) {
        List<Task> tasks = jdbcTemplate.query(GET_TASK_COUNT_BY_USER_ID, new TaskRowMapper(), userId);
        return (long)tasks.size();
    }
}
