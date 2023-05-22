package com.example.springblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


import static org.springframework.security.config.Customizer.withDefaults;

//Spring Security Configuration

@Configuration
public class SpringSecurityConfig {

    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SpringSecurityConfig(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    // HTTPSecurity configurer

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()

                )
                .httpBasic(withDefaults());

        return http.build();
    }
}