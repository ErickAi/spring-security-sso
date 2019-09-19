package org.securityclient.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.securityclient.AuthUser;
import org.securityclient.domain.User;
import org.securityclient.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    @Override
    public AuthUser loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        return new AuthUser(optionalUser.orElseThrow(
            () -> new UsernameNotFoundException("User '" + email + "' was not found")));
    }
}