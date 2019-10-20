package com.spring.project.service.user;

import com.spring.project.model.User;

import java.util.Optional;

public interface UserDao {
    boolean isExist(User user);
    void add(User user);
    Optional<Long> findUserIdByEmailAndPassword(String email, String password);
}
