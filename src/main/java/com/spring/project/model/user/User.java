package com.spring.project.model.user;

import com.spring.project.security.UserRole;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class User {
    private Long userId;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String subscription;
    @NonNull
    private UserRole userRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
