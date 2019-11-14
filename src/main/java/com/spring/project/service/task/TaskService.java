package com.spring.project.service.task;

import com.spring.project.domain.task.Task;
import com.spring.project.domain.task.TaskPriority;

import java.util.List;

public interface TaskService {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    List<Task> findAllTasksByUser(Long userId);
    void closeTask(Long taskId);
    void openTask(Long taskId);
    void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority);
}
