package com.spring.project.service.user;

import com.spring.project.controller.user.UserService;
import com.spring.project.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean signUp(User user) {
        if (userDao.isExist(user)) {
            return false;
        } else {
            userDao.add(user);
            return true;
        }
    }
}
