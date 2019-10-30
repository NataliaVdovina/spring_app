package com.spring.project;

import com.spring.project.config.ApplicationConfig;
import com.spring.project.controller.task.TaskController;
import com.spring.project.controller.user.UserController;
import com.spring.project.model.task.Task;
import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        UserController userController = ctx.getBean(UserController.class);
        User user = new User("testEmail1", "testPassword1", "testFirstName1",
                "testLastName1", UserRole.USER);
        assert !userController.signUp(user);
        assert userController.signIn(user);


        TaskController taskController = ctx.getBean(TaskController.class);
        taskController.createTask("taskName");

        Set<Task> allTasksByUser = taskController.findAllTasksByUser();
        assert allTasksByUser.size() == 1;

        Task task = allTasksByUser.stream()
                .findAny()
                .orElse(null);
        taskController.deleteTask(task.getTaskId());

        allTasksByUser = taskController.findAllTasksByUser();
        assert allTasksByUser.size() == 0;
    }
}
