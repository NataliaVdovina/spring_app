package com.spring.project.dao;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskStatus;
import com.spring.project.service.task.TaskDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class TaskDaoImpl implements TaskDao {
    private Set<Task> tasks = new HashSet<>();

    @PostConstruct
    public void init(){
        Task task1 = new Task(1L, 1L, "testTaskName1", TaskStatus.OPEN);
        Task task2 = new Task(2L, 1L, "testTaskName2", TaskStatus.OPEN);
        tasks.add(task1);
        tasks.add(task2);
    }


    @Override
    public void createTask(Long userId, String taskName) {
        Optional<Long> maxTaskId = tasks.stream()
                .map(Task::getTaskId)
                .max(Collections.reverseOrder());
        Long taskId = maxTaskId.orElse(0L) + 1;
        Task task = new Task(taskId, userId, taskName, TaskStatus.OPEN);
        tasks.add(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        tasks.removeIf(task -> task.getTaskId().equals(taskId));
    }

    @Override
    public Set<Task> findAllTasksByUser(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUserId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public void setStatus(Long taskId, TaskStatus taskStatus) {
        tasks.stream().filter(task -> task.getTaskId().equals(taskId)).forEach(task -> task.setTaskStatus(taskStatus));
    }

    @Override
    public Long getTaskCountByUserId(Long userId) {
        return tasks.stream()
                .filter(task -> task.getUserId().equals(userId))
                .count();
    }
}
