package com.spring.project.controller.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthenticationService authenticationService;

    @PostMapping("/createTask/{taskName}")
    public void createTask(@PathVariable String taskName) {
        taskService.createTask(authenticationService.getUserId(), taskName);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.deleteTask(taskId);
    }

    @GetMapping("/findAllTaskByUser")
    public List<Task> findAllTasksByUser() {
        return taskService.findAllTasksByUser(authenticationService.getUserId());
    }

    @PutMapping("/closeTask/{taskId}")
    public void closeTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.closeTask(taskId);
    }

    @PutMapping("/openTask/{taskId}")
    public void openTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.openTask(taskId);
    }

    @PutMapping("/setTaskPriority/{taskId}")
    public void setTaskPriority(@PathVariable Long taskId, TaskPriority taskPriority) {
        taskService.setTaskPriorityByTaskId(taskId, taskPriority);
    }
}
