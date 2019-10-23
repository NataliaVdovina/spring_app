package com.spring.project.dao;

import com.spring.project.model.user.User;
import com.spring.project.service.user.UserDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
    private Set<User> users = new HashSet<>();

    @PostConstruct
    public void init() {
        User user1 = new User(1L, "testEmail1", "testPassword1", "testFirstName1",
                "testLastName1");
        User user2 = new User(2L, "testEmail2", "testPassword2", "testFirstName2",
                "testLastName2");
        users.add(user1);
        users.add(user2);
    }

    @Override
    public boolean isExist(User user) {
        return users.contains(user);
    }

    @Override
    public void createUser(User user) {
        Optional<Long> maxUserId = users.stream()
                .map(User::getUserId)
                .max(Collections.reverseOrder());
        user.setUserId(maxUserId.orElse(0L) + 1);
        users.add(user);
    }

    @Override
    public Optional<Long> findUserIdByEmailAndPassword(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .map(User::getUserId)
                .findAny();
    }

    @Override
    public void setSubscriptionByUserId(Long userId, String subscription) {
        users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .forEach(user -> user.setSubscription(subscription));
    }
}
