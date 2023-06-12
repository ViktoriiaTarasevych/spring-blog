package com.example.springblog.config;

import com.example.springblog.repository.RoleRepository;
import com.example.springblog.repository.UserRepository;
import com.example.springblog.service.UserService;
import com.example.springblog.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerMapping;


import static org.springframework.security.config.Customizer.withDefaults;


//Spring Security Configuration

@Configuration
public class SpringSecurityConfig {

    private final AccessDeniedHandler accessDeniedHandler;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImp(userRepository, roleRepository, bCryptPasswordEncoder());
    }

    @Autowired
    public SpringSecurityConfig(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    // HTTPSecurity configurer

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }
}