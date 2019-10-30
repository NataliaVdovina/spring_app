package com.spring.project.dao;

import com.spring.project.model.user.User;

import java.util.Optional;

public interface UserRepository {
    boolean isExist(User user);
    void createUser(User user);
    Optional<Long> findUserIdByEmailAndPassword(String email, String password);
    void setSubscriptionByUserId(Long userId, String subscription);
    Optional<User> findUserById(Long userId);
    Optional<User> updateUser(User user);
    void deleteUserById(Long id);
}
