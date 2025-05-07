package io.qualitymatters.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        System.err.println("Custom SecurityConfig loaded");


        try {
            http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/login"
                ).permitAll()
                .anyRequest().authenticated())
            .httpBasic(withDefaults());

        return http.build();
        } catch (Exception e) {
            System.err.println("Error configuring SecurityFilterChain: " + e.getMessage());
            e.printStackTrace();
            throw new IllegalStateException("Failed to configure HTTP security", e);
        }
    }


    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
            .password("{noop}password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}