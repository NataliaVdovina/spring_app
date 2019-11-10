package com.spring.project.service.user;

import com.spring.project.dao.UserRepository;
import com.spring.project.model.user.User;
import com.spring.project.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    static final String SECRET_HASH = md5Hash("secret");

    @Override
    public boolean signUp(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public long signIn(User user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("User", "email", user.getEmail()))
                .getUserId();
    }

    @Override
    public void buySubscription(Long userId) {
        User user = getUserById(userId);
        user.setSubscription(SECRET_HASH);
        userRepository.save(user);
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
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
    }
}
