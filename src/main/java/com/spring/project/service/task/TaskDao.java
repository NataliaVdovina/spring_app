package com.spring.project.service.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskStatus;

import java.util.Set;

public interface TaskDao {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    Set<Task> findAllTasksByUser(Long userId);
    void setStatus(Long taskId, TaskStatus taskStatus);
}
