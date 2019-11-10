package com.spring.project.controller.user;

import com.spring.project.model.user.User;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody User user) {
        long userId = userService.signIn(user);
        authenticationService.setUserId(userId);
    }

    @PostMapping("/bySubscription")
    @ResponseStatus(HttpStatus.OK)
    public void buySubscription(){
        userService.buySubscription(authenticationService.getUserId());
    }
}
