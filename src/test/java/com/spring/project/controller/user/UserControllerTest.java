package com.spring.project.controller.user;

import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.task.TaskService;
import com.spring.project.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserController userController;

    @Test
    void signUp() {

    }

    @Test
    void signIn() {

    }

    @Test
    void buySubscription() {

    }
}