package com.spring.project.dao.user;

import com.spring.project.dao.task.TaskRowMapper;
import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static com.spring.project.dao.user.UserRepositoryImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    private static final User USER = MockData.user();

    @Test
    void isExist() {
        when(jdbcTemplate.queryForObject(eq(SELECT_BY_ID), any (UserRowMapper.class), eq(USER.getUserId())))
                .thenReturn(USER);
        boolean exist = userRepositoryImpl.isExist(USER);
        verify(jdbcTemplate).queryForObject(eq(SELECT_BY_ID), any (UserRowMapper.class), eq(USER.getUserId()));
        assertTrue(exist);
    }

    @Test
    void createUser() {
        userRepositoryImpl.createUser(USER);
        verify(jdbcTemplate).update(
                INSERT, USER.getEmail(), USER.getPassword(), USER.getFirstName(), USER.getLastName(),
                USER.getUserRole());
    }

    @Test
    void findUserIdByEmailAndPassword() {
        when(jdbcTemplate.queryForObject(SELECT_ID_BY_EMAIL_AND_PASSWORD, Long.class, USER.getEmail(), USER.getPassword()))
                .thenReturn(USER.getUserId());
        Optional<Long> actualUserIdByEmailAndPassword = userRepositoryImpl.findUserIdByEmailAndPassword(USER.getEmail(),
                USER.getPassword());
        verify(jdbcTemplate).queryForObject(SELECT_ID_BY_EMAIL_AND_PASSWORD, Long.class, USER.getEmail(), USER.getPassword());
        assertEquals(USER.getUserId(), actualUserIdByEmailAndPassword.orElseThrow(RuntimeException::new));
    }

    @Test
    void setSubscriptionByUserId() {
        userRepositoryImpl.setSubscriptionByUserId(USER.getUserId(), USER.getSubscription());
        verify(jdbcTemplate).update(UPDATE_SUBSCRIPTION, USER.getSubscription(), USER.getUserId());
    }

    @Test
    void findUserById() {
        when(jdbcTemplate.queryForObject(eq(SELECT_BY_ID), any (UserRowMapper.class), eq(USER.getUserId())))
                .thenReturn(USER);

        Optional<User> actualOptionalUser = userRepositoryImpl.findUserById(USER.getUserId());
        verify(jdbcTemplate).queryForObject(eq(SELECT_BY_ID), any (UserRowMapper.class), eq(USER.getUserId()));
        assertEquals(USER, actualOptionalUser.orElseThrow(RuntimeException::new));
    }

    @Test
    void updateUser() {
        when(jdbcTemplate.queryForObject(eq(UPDATE), any (UserRowMapper.class), eq(USER)))
                .thenReturn(USER);

        Optional<User> actualOptionalUser = userRepositoryImpl.updateUser(USER);
        verify(jdbcTemplate).queryForObject(eq(UPDATE), any (UserRowMapper.class), eq(USER));
        assertEquals(USER, actualOptionalUser.orElseThrow(RuntimeException::new));
    }

    @Test
    void deleteUserById() {
        userRepositoryImpl.deleteUserById(USER.getUserId());
        verify(jdbcTemplate).update(DELETE, USER.getUserId());
    }

    private static class MockData {
        private static User user() {
            return User.builder()
                    .userId(1L)
                    .email("email")
                    .password("password")
                    .firstName("firstName")
                    .lastName("lastName")
                    .subscription("subscription")
                    .userRole(UserRole.ADMIN)
                    .build();
        }
    }
}