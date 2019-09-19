package org.securityclient;

import lombok.Getter;
import org.securityclient.domain.Role;
import org.securityclient.domain.User;
import org.securityclient.util.ValidationUtil;
import org.springframework.lang.NonNull;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {
    @NonNull
    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public boolean hasRole(Role role) {
        return user.getRoles().contains(role);
    }

    public void checkRole(Role role) {
        ValidationUtil.checkRole(user.getRoles(), role);
    }

    public int id() {
        return user.id();
    }

    @Override
    public String toString() {
        return "AuthUser{" +
            "email='" + user.getEmail() + '\'' +
            ", id=" + user.getId() +
            ", roles=" + user.getRoles() +
            '}';
    }
}