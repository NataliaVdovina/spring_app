package com.spring.project.service.task;

import com.spring.project.controller.task.TaskService;
import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    @Override
    public void createTask(Long userId, String taskName) {
        taskDao.createTask(userId,taskName);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskDao.deleteTask(taskId);
    }

    @Override
    public Set<Task> findAllTasksByUser(Long userId) {
        return taskDao.findAllTasksByUser(userId);
    }

    @Override
    public void closeTask(Long taskId) {
        taskDao.setStatus(taskId, TaskStatus.CLOSED);
    }

    @Override
    public void openTask(Long taskId) {
        taskDao.setStatus(taskId, TaskStatus.OPEN);
    }
}
