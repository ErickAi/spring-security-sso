package org.securityclient.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.securityclient.AuthorizedUser;
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
    public AuthorizedUser loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        return new AuthorizedUser(optionalUser.orElseThrow(
            () -> new UsernameNotFoundException("User '" + email + "' was not found")));
    }
}