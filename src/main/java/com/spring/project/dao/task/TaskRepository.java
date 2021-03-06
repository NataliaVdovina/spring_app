package com.spring.project.dao.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.task.TaskStatus;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

public interface TaskRepository {
    void createTask(Long userId, String taskName);
    void deleteTask(Long taskId);
    List<Task> findAllTasksByUser(Long userId);
    void setStatus(Long taskId, TaskStatus taskStatus);
    Long getTaskCountByUserId(Long userId);
    void setTaskPriorityByTaskId(Long taskId, TaskPriority taskPriority);
    void putFile(Blob file, Long taskId);
}
