package com.spring.project.service.user;

import com.spring.project.controller.user.UserService;
import com.spring.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public boolean signUp(User user) {
        if (userDao.isExist(user)) {
            return false;
        } else {
            userDao.add(user);
            return true;
        }
    }

    @Override
    public Optional<Long> signIn(User user) {
        return userDao.findUserIdByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
