package com.spring.project.service.user;

import com.spring.project.model.user.User;

import java.util.Optional;

public interface UserDao {
    boolean isExist(User user);
    void createUser(User user);
    Optional<Long> findUserIdByEmailAndPassword(String email, String password);
    void setSubscriptionByUserId(Long userId, String subscription);
    Optional<User> findUserById(Long userId);
}
