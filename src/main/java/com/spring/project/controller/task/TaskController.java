package com.spring.project.controller.task;

import org.springframework.stereotype.Component;

@Component
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
}
