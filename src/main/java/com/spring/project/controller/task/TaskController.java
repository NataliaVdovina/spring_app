package com.spring.project.controller.task;

import com.spring.project.model.task.Task;
import com.spring.project.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthenticationService authenticationService;

    public void createTask(String taskName) {
        taskService.createTask(authenticationService.getUserId(), taskName);
    }

    public void deleteTask(Long taskId) {
        authenticationService.checkAuthentication();
        taskService.deleteTask(taskId);
    }

    public Set<Task> findAllTasksByUser() {
        return taskService.findAllTasksByUser(authenticationService.getUserId());
    }

    public void closeTask(Long taskId) {
        authenticationService.checkAuthentication();
        taskService.closeTask(taskId);
    }

    public void openTask(Long taskId) {
        authenticationService.checkAuthentication();
        taskService.openTask(taskId);
    }
}
