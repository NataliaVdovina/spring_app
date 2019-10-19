package com.spring.project.controller.user;

import org.springframework.stereotype.Component;

@Component
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
