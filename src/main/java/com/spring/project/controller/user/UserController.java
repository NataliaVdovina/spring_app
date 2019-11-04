package com.spring.project.controller.user;

import com.spring.project.model.user.User;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/user")
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
    public boolean signIn(@RequestBody User user) {
        Optional<Long> optionalUserId = userService.signIn(user);
        optionalUserId.ifPresent(authenticationService::setUserId);
        return optionalUserId.isPresent();
    }

    @PostMapping("/bySubscription")
    @ResponseStatus(HttpStatus.OK)
    public void buySubscription(){
        userService.buySubscription(authenticationService.getUserId());
    }
}
