package io.qualitymatters.restfulapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET, "/bookings/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/bookings/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/bookings/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/bookings/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
