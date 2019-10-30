package com.spring.project.service.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;

import java.util.Set;

public interface TaskService {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    Set<Task> findAllTasksByUser(Long userId);
    void closeTask(Long taskId);
    void openTask(Long taskId);
    void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority);
}
