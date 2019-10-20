package com.spring.project.controller.user;

import com.spring.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private Optional<Long> optionalUserId;

    public boolean signUp(User user) {
        return userService.signUp(user);
    }

    public boolean signIn(User user) {
        optionalUserId = userService.signIn(user);
        return optionalUserId.isPresent();
    }
}
