package com.spring.project.service.user;

import com.spring.project.dao.UserRepository;
import com.spring.project.model.user.User;
import com.spring.project.security.UserRole;
import com.spring.project.service.exception.NotFoundException;
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

    private static User user;

    @BeforeEach
    private void init() {
        user = MockData.user();
    }

    @Test
    void signUpUserExists() {
        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        boolean userCreated = userServiceImpl.signUp(user);

        verify(userRepository).findByEmail(user.getEmail());
        verify(userRepository, never()).save(any());
        assertFalse(userCreated);
    }

    @Test
    void signUpUserNotExists() {
        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        boolean userCreated = userServiceImpl.signUp(user);

        verify(userRepository).save(user);
        verify(userRepository).findByEmail(user.getEmail());
        assertTrue(userCreated);
    }

    @Test
    void signIn() {
        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        long userId = userServiceImpl.signIn(user);

        verify(userRepository).findByEmail(user.getEmail());
        assertEquals(user.getUserId(), userId);
    }

    @Test
    void buySubscription() {
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        userServiceImpl.buySubscription(user.getUserId());

        verify(userRepository).save(user);
        assertEquals(SECRET_HASH, user.getSubscription());
    }

    @Test
    void checkSubscription_no() {
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        boolean checkSubscription = userServiceImpl.checkSubscription(user.getUserId());

        assertFalse(checkSubscription);
    }

    @Test
    void checkSubscription_yes() {
        user.setSubscription("secret");
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        boolean checkSubscription = userServiceImpl.checkSubscription(user.getUserId());

        assertTrue(checkSubscription);
    }

    @Test
    void getUserById_exists() {
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        User actualUser = userServiceImpl.getUserById(user.getUserId());

        verify(userRepository).findById(user.getUserId());
        assertEquals(actualUser, user);
    }

    @Test
    void getUserById_notExists() {
        when(userRepository.findById(user.getUserId()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userServiceImpl.getUserById(user.getUserId()));
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