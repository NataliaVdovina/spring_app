package com.spring.project.dao.user;

import com.spring.project.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final String INSERT = "insert into user (email, password, first_name, last_name, userRole) " +
            "values(?,?,?,?,?)";
    private final String UPDATE_SUBSCRIPTION = "update user set subscription=? where user_id=?";
    private final String SELECT_ID_BY_EMAIL_AND_PASSWORD = "select user_id from user where email=? and password=?";
    private final String SELECT_BY_ID = "select * from user where id=?";
//    private final String UPDATE = "update user SET password=?, first_name=?, last_name=?, subscription=? WHERE user_id = ?";
//    private final String DELETE = "delete from user where id=?";

    private final JdbcOperations jdbcOperations;

    @Override
    public boolean isExist(User user) {
        Optional<User> optionalUser = findUserById(user.getUserId());
        return optionalUser.isPresent();
    }

    @Override
    public void createUser(User user) {
        jdbcOperations.update(
                INSERT, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getUserRole()
        );
    }

    @Override
    public Optional<Long> findUserIdByEmailAndPassword(String email, String password) {
        Long userId = jdbcOperations.queryForObject(SELECT_ID_BY_EMAIL_AND_PASSWORD, Long.class, email, password);
        return Optional.ofNullable(userId);
    }

    @Override
    public void setSubscriptionByUserId(Long userId, String subscription) {
        jdbcOperations.update(UPDATE_SUBSCRIPTION, subscription, userId);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        User user = jdbcOperations.queryForObject(SELECT_BY_ID, new UserRowMapper(), userId);
        return Optional.ofNullable(user);
    }

//    @Override
//    public Optional<User> updateUser(User user) {
//        User updateUser = jdbcTemplate.queryForObject(UPDATE, new UserRowMapper(), user);
//        return Optional.of(updateUser);
//    }
//
//    @Override
//    public void deleteUserById(Long id) {
//        jdbcTemplate.update(DELETE, id);
//    }
}
