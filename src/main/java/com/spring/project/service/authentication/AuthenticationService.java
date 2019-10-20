package com.spring.project.service.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthenticationService {
    private Long userId;

    public Long getUserId() {
        if(userId == null) {
            throw new AuthenticationException("No user is logged in");
        }
        return userId;
    }

    public void checkAuthentication() {
        getUserId();
    }
}