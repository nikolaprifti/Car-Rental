package com.car.rental.service.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.car.rental.service.dao.Role;
import com.car.rental.service.repositories.RoleRepository;
import com.car.rental.service.security.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig implements CommandLineRunner, WebMvcConfigurer {

    private final UserDetailsServiceImpl userService;
    private final RoleRepository roleRepository;
    @Value(value = "${frontendUrl}")
    private String frontendUrl;

    private static final String[] allowedUrls = new String[] { "/employee/create", "/employee/all", "/employee/login" };

    public SecurityConfig(UserDetailsServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authorizeHttpRequests(request -> {
            request.requestMatchers(allowedUrls).permitAll()
                    .anyRequest().authenticated();
        })
                .authenticationManager(authenticationManager)
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontendUrl)
                .allowedMethods("*");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void run(String... args) {
        if (!roleRepository.existsById("ROLE_MANAGER")) {
            Role role = Role.builder()
                    .roleId("ROLE_MANAGER")
                    .build();
            roleRepository.save(role);
        }
        if (!roleRepository.existsById("ROLE_EMPLOYEE")) {
            Role role = Role.builder()
                    .roleId("ROLE_EMPLOYEE")
                    .build();
            roleRepository.save(role);
        }
    }
}
