package com.spring.project.controller.task;

import com.spring.project.model.task.Task;
import com.spring.project.model.task.TaskPriority;
import com.spring.project.model.user.User;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody String taskName) {
        taskService.createTask(authenticationService.getUserId(), taskName);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.deleteTask(taskId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAllTasksByUser() {
        return taskService.findAllTasksByUser(authenticationService.getUserId());
    }

    @PatchMapping("/{taskId}/close")
    @ResponseStatus(HttpStatus.OK)
    public void closeTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.closeTask(taskId);
    }

    @PatchMapping("/{taskId}/open")
    @ResponseStatus(HttpStatus.OK)
    public void openTask(@PathVariable Long taskId) {
        authenticationService.checkAuthentication();
        taskService.openTask(taskId);
    }

    @PatchMapping("/{taskId}/setPriority")
    @ResponseStatus(HttpStatus.OK)
    public void setTaskPriority(@PathVariable Long taskId, @RequestBody TaskPriority taskPriority) {
        taskService.setTaskPriorityByTaskId(taskId, taskPriority);
    }

    @PostMapping("/putFile")
    public void putFile(User user, @RequestParam("file") Blob file, Long taskId) {
        taskService.putFile(user, file, taskId);
    }
}
