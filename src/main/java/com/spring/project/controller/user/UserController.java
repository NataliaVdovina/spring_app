package com.spring.project.controller.user;

import com.spring.project.model.user.User;
import com.spring.project.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public boolean signUp(User user) {
        return userService.signUp(user);
    }

    public boolean signIn(User user) {
        Optional<Long> optionalUserId = userService.signIn(user);
        optionalUserId.ifPresent(authenticationService::setUserId);
        return optionalUserId.isPresent();
    }

    public void buySubscription(){
        userService.buySubscription(authenticationService.getUserId());
    }
}
