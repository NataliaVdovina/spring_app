package com.spring.project.dao.user;

import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String subscription = resultSet.getString("subscription");
        String  userRole = resultSet.getString("user_role");
        return User.builder()
                .userId(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .subscription(subscription)
                .userRole(userRole)
                .build();
    }
}
