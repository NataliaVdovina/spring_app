package com.spring.project.dao;

import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final String INSERT = "insert into user (email, password, first_name, last_name, userRole) "+
            "values(?,?,?,?,?)";
    private final String SELECT_BY_EMAIL_AND_PASSWORD = "select user_id from user where email=? and password=?";
    private final String SELECT_BY_ID = "select * from user where id=?";
    private final String UPDATE = "update user SET password=?, first_name=?, last_name=?, subscription=? WHERE user_id = ?";
    private final String DELETE = "delete from user where id=?";

    @NonNull
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        User user1 = new User ("testEmail1", "testPassword1", "testFirstName1",
                "testLastName1", UserRole.USER);
        User user2 = new User("testEmail2", "testPassword2", "testFirstName2",
                "testLastName2", UserRole.ADMIN);
        createUser(user1);
        createUser(user2);
    }

    @Override
    public boolean isExist(User user) {
        Optional<User> optionalUser = findUserById(user.getUserId());
        return optionalUser.isPresent();
    }

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(INSERT, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName()
        , user.getUserRole());
    }

    @Override
    public Optional<Long> findUserIdByEmailAndPassword(String email, String password) {

    }

    @Override
    public void setSubscriptionByUserId(Long userId, String subscription) {
        findUserById(userId)
                .ifPresent(user -> user.setSubscription(subscription));
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        User user = jdbcTemplate.queryForObject(SELECT_BY_ID, new UserRowMapper(), userId);
        return Optional.of(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User updateUser = jdbcTemplate.queryForObject(UPDATE, new UserRowMapper(), user);
        return Optional.of(updateUser);
    }

    @Override
    public void deleteUserById(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    public void createTable(String name){
        jdbcTemplate.execute("create table "+ name + "id integer, name varchar)");
    }
}
