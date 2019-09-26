package org.securityclient;

import lombok.Getter;
import org.securityclient.domain.Role;
import org.securityclient.domain.User;
import org.securityclient.util.ValidationUtil;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class AuthorizedUser implements UserDetails, OAuth2User {
    @NonNull
    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public AuthorizedUser(User user) {
        this.user = user;
        this.authorities = user.getRoles();
    }

    public static AuthorizedUser create(User user, Map<String, Object> attributes) {
        AuthorizedUser authorizedUser = new AuthorizedUser(user);
        authorizedUser.attributes = attributes;
        return authorizedUser;
    }


    public int id() {
        return user.id();
    }

    public boolean hasRole(Role role) {
        return user.getRoles().contains(role);
    }

    public void checkRole(Role role) {
        ValidationUtil.checkRole(user.getRoles(), role);
    }

    @Override   // UserDetails
    public String getUsername() {
        return user.getEmail();
    }

    @Override   // OAuth2User
    public String getName() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "AuthorizedUser{" +
            "\n  user=" + user +
            "\n  authorities=" + authorities +
            "\n  attributes=" + attributes +
            '}';
    }
}