package com.spring.project;

import com.spring.project.config.ApplicationConfig;
import com.spring.project.controller.user.UserController;
import com.spring.project.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        UserController userController = ctx.getBean(UserController.class);

        User user = new User(null, "testEmail1", "testPassword1", "testFirstName1",
                "testLastName1");
        assert !userController.signUp(user);
        assert userController.signIn(user);
    }
}
