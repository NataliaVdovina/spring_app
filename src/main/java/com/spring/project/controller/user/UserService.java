package com.spring.project.controller.user;

import com.spring.project.model.user.User;

import java.util.Optional;

public interface UserService {
    boolean signUp(User user);
    Optional<Long> signIn(User user);

    void buySubscription(Long userId);
}
