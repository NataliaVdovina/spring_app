package com.spring.project.domain.user;

public enum UserRole {

    ADMIN("ADMIN"), USER("USER");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
