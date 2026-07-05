package com.home.prj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//Why create a Bean?

//Instead of writing:
//PasswordEncoder encoder = new BCryptPasswordEncoder();
//everywhere, Spring creates one managed instance.
//Now we can inject it anywhere:
//private final PasswordEncoder passwordEncoder;
//This follows the Dependency Injection pattern.