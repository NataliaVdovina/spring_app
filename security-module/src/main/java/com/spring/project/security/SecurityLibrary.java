package com.spring.project.security;

public class SecurityLibrary {
    public boolean isAdmin(UserRole userRole) {
        return UserRole.ADMIN == userRole;
    }
}
