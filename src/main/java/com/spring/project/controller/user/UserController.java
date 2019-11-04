package com.spring.project.controller.user;

import com.spring.project.model.user.User;
import com.spring.project.service.authentication.AuthenticationService;
import com.spring.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/user")
    public boolean signUp(User user) {
        return userService.signUp(user);
    }

    @GetMapping("/user/{id}")
    public boolean signIn(@RequestBody User user, @PathVariable String id) {
        Optional<Long> optionalUserId = userService.signIn(user);
        optionalUserId.ifPresent(authenticationService::setUserId);
        return optionalUserId.isPresent();
    }

    @PutMapping("/subscribe")
    public void buySubscription(){
        userService.buySubscription(authenticationService.getUserId());
    }
}
