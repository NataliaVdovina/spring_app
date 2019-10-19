package com.spring.project.service.user;

import com.spring.project.model.User;

public interface UserDao {
    boolean isExist(User user);
    void add(User user);
}
