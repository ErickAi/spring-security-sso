package org.securityclient.config;

import lombok.AllArgsConstructor;
import org.securityclient.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final PasswordEncoder DELEGATING_PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(DELEGATING_PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.antMatcher("/**")
            .formLogin()
            .loginPage("/login")//.permitAll()
//            .failureUrl("/login?error").permitAll()
            .and().authorizeRequests()
            .antMatchers("/resources/**", "/", "/login**", "/index").permitAll()
            .antMatchers("/profile/**", "/secured-page").authenticated()
            .antMatchers("/**").hasAuthority(Role.ROLE_ADMIN.getAuthority())
            .anyRequest().authenticated()
            .and().cors()
            .and().csrf().disable();

//        http.cors()
//                .and().authorizeRequests()
//                .anyRequest().permitAll()
//                .and().csrf().disable();
    }

/*
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**")
            .permitAll()
            .anyRequest()
            .authenticated();
    }
*/
}
