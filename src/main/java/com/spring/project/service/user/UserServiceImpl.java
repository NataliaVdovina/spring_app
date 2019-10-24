package com.spring.project.service.user;

import com.spring.project.controller.user.UserService;
import com.spring.project.model.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private static final String SECRET_HASH = md5Hash("secret");

    @Override
    public boolean signUp(User user) {
        if (userDao.isExist(user)) {
            return false;
        } else {
            userDao.createUser(user);
            return true;
        }
    }

    @Override
    public Optional<Long> signIn(User user) {
        return userDao.findUserIdByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public void buySubscription(Long userId) {
        userDao.setSubscriptionByUserId(userId, SECRET_HASH);
    }

    private static String md5Hash(String s) {
        return DigestUtils.md5Hex(s);
    }

    @Override
    public boolean checkSubscription(Long userId) {
        Optional<User> optionalUser = userDao.findUserById(userId);
        User user = optionalUser.orElseThrow(UserNotFoundException::new);

        String subscription = user.getSubscription();
        return SECRET_HASH.equals(md5Hash(subscription));
    }
}
