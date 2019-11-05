package com.spring.project.service.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.user.User;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

public interface TaskService {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    List<Task> findAllTasksByUser(Long userId);
    void closeTask(Long taskId);
    void openTask(Long taskId);
    void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority);
    void putFile(User user, Blob file, Long taskId);
}
