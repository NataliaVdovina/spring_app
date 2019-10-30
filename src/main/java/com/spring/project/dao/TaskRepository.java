package com.spring.project.dao;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;

import java.util.Set;

public interface TaskRepository {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    Set<Task> findAllTasksByUser(Long userId);
    void setStatus(Long taskId, TaskStatus taskStatus);
    Long getTaskCountByUserId(Long userId);
    void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority);
}
