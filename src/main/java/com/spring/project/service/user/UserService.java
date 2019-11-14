package com.spring.project.service.user;

import com.spring.project.domain.user.User;

public interface UserService {
    boolean signUp(User user);
    long signIn(User user);

    void buySubscription(Long userId);
    boolean checkSubscription(Long userId);

    User getUserById(Long userId);
}
