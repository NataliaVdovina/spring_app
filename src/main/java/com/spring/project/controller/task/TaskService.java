package com.spring.project.controller.task;

import com.spring.project.model.Task;
import com.spring.project.model.TaskStatus;

import java.util.Set;

public interface TaskService {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    Set<Task> findAllTasksByUser(Long userId);
    void closeTask(Long taskId);
    void openTask(Long taskId);
}
