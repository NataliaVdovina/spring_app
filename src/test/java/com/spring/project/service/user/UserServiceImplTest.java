package com.spring.project.service.user;

import com.spring.project.dao.user.UserRepository;
import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.spring.project.service.user.UserServiceImpl.SECRET_HASH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private static User USER;

    @BeforeEach
    private void init() {
        USER = MockData.user();
    }

    @Test
    void signUpUserExists() {
        when(userRepository.isExist(USER))
                .thenReturn(true);

        boolean userCreated = userServiceImpl.signUp(USER);

        verify(userRepository).isExist(USER);
        verify(userRepository, never()).createUser(USER);
        assertFalse(userCreated);
    }

    @Test
    void signUpUserNotExists() {
        when(userRepository.isExist(USER))
                .thenReturn(false);

        boolean userCreated = userServiceImpl.signUp(USER);

        verify(userRepository).createUser(USER);
        verify(userRepository).isExist(USER);
        assertTrue(userCreated);
    }

    @Test
    void signIn() {
        when(userRepository.findUserIdByEmailAndPassword(USER.getEmail(), USER.getPassword()))
                .thenReturn(Optional.of(USER.getUserId()));

        Optional<Long> optionalUserId = userServiceImpl.signIn(USER);

        verify(userRepository).findUserIdByEmailAndPassword(USER.getEmail(), USER.getPassword());
        assertEquals(Optional.of(USER.getUserId()), optionalUserId);
    }

    @Test
    void buySubscription() {
        userServiceImpl.buySubscription(USER.getUserId());
        verify(userRepository).setSubscriptionByUserId(USER.getUserId(), SECRET_HASH);
    }

    @Test
    void checkSubscription_no() {
        when(userRepository.findUserById(USER.getUserId()))
                .thenReturn(Optional.of(USER));
        boolean checkSubscription = userServiceImpl.checkSubscription(USER.getUserId());
        assertFalse(checkSubscription);
    }

    @Test
    void checkSubscription_yes() {
        USER.setSubscription("secret");
        when(userRepository.findUserById(USER.getUserId()))
                .thenReturn(Optional.of(USER));

        boolean checkSubscription = userServiceImpl.checkSubscription(USER.getUserId());

        assertTrue(checkSubscription);
    }

    @Test
    void getUserById_exists() {
        when(userRepository.findUserById(USER.getUserId()))
                .thenReturn(Optional.of(USER));

        User actualUser = userServiceImpl.getUserById(USER.getUserId());

        verify(userRepository).findUserById(USER.getUserId());
        assertEquals(actualUser, USER);
    }

    @Test
    void getUserById_notExists() {
        when(userRepository.findUserById(USER.getUserId()))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserById(USER.getUserId()));
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