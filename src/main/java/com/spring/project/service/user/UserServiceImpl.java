package com.spring.project.service.user;

import com.spring.project.dao.UserRepository;
import com.spring.project.model.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final String SECRET_HASH = md5Hash("secret");

    @Override
    public boolean signUp(User user) {
        if (userRepository.isExist(user)) {
            return false;
        } else {
            userRepository.createUser(user);
            return true;
        }
    }

    @Override
    public Optional<Long> signIn(User user) {
        return userRepository.findUserIdByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public void buySubscription(Long userId) {
        userRepository.setSubscriptionByUserId(userId, SECRET_HASH);
    }

    private static String md5Hash(String s) {
        return DigestUtils.md5Hex(s);
    }

    @Override
    public boolean checkSubscription(Long userId) {
        User user = getUserById(userId);
        String subscription = user.getSubscription();
        return SECRET_HASH.equals(md5Hash(subscription));
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userById = userRepository.findUserById(userId);
        return userById.orElseThrow(UserNotFoundException::new);
    }
}
