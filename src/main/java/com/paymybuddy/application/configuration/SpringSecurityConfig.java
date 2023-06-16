package com.paymybuddy.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfiguration {
/*
    /**
     * Store the user details
     *
     * @return InMemoryUserDetailsManager
     *
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("appUser")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("appAdmin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(user1, admin);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Secure the http access
     *
     * @param http HttpSecurity
     * @return http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated()
                ).formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
        .exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/403"));

        return http.build();
    }


    /**
     *Bcrypt uses hash algorithm to store password
     *
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
