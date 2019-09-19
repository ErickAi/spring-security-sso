package org.securityclient.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.securityclient.AuthUser;
import org.securityclient.domain.User;
import org.securityclient.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = SecurityController.URL)
@AllArgsConstructor
@Slf4j
public class SecurityController {
    static final String URL = "/profile";

    private final UserRepository userRepository;

    @GetMapping
    private String get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get by {}", authUser);
        return "secured-page";
    }
}
