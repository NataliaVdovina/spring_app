package com.spring.project;

import com.spring.project.config.DbConfig;
import com.spring.project.dao.task.TaskRepository;
import com.spring.project.dao.user.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        TaskRepository taskRepository = context.getBean(TaskRepository.class);
        System.out.println(userRepository.findUserById(1L));
        System.out.println(taskRepository.findAllTasksByUser(1L));
    }
}
