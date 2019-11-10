package com.spring.project.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityLibrary {
    public boolean isAdmin(UserRole userRole) {
        return UserRole.ADMIN == userRole;
    }
}
