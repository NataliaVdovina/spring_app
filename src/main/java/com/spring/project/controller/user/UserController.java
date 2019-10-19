package com.spring.project.controller.user;

import com.spring.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean signUp(User user){
        return userService.signUp(user);
    }
}
