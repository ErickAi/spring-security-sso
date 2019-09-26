package org.securityclient.config;

import lombok.AllArgsConstructor;
import org.securityclient.security.OAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;


@Configuration
@AllArgsConstructor
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final PasswordEncoder DELEGATING_PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private UserDetailsService userDetailsService;
    private OAuth2UserService oAuth2UserService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(DELEGATING_PASSWORD_ENCODER);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        return new DefaultAuthorizationCodeTokenResponseClient();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http // @formatter:off
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(permittedMatchers()).permitAll()
//                .antMatchers("/**").hasAuthority(Role.ROLE_ADMIN.getAuthority())
                .anyRequest().authenticated()
            .and().cors()
            .and().formLogin()
                .loginPage("/login").loginProcessingUrl("/login-email")
                .defaultSuccessUrl("/profile")
            .and()
                .oauth2Login()
                .loginPage("/login")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize-client")
                .authorizationRequestRepository(authorizationRequestRepository())
              .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
              .and()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
              .and()
                .tokenEndpoint()
                .accessTokenResponseClient(accessTokenResponseClient())
              .and()
                .defaultSuccessUrl("/profile")
                .failureUrl("/login?error");
        //        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.cors()
//                .and().authorizeRequests()
//                .anyRequest().permitAll()
//                .and().csrf().disable();
    } // @formatter:off

    private String[] permittedMatchers() {
        return new String[]{
            "/", "/resources/**", "/webjars/**", "/login/**", "/index"
        };
    }
}
