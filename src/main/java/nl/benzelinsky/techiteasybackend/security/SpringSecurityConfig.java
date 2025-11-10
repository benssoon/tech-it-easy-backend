package nl.benzelinsky.techiteasybackend.security;

import nl.benzelinsky.techiteasybackend.filter.JwtRequestFilter;
import nl.benzelinsky.techiteasybackend.repositories.UserRepository;
import nl.benzelinsky.techiteasybackend.services.CustomUserDetailsService;
import nl.benzelinsky.techiteasybackend.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

private final JwtUtil jwtUtil;
private final UserRepository userRepository;
private final CustomUserDetailsService customUserDetailsService;

public SpringSecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, CustomUserDetailsService ud) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepo;
    this.customUserDetailsService = ud;
}
    /*@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService);
        return new ProviderManager(auth);
    }*/
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .jdbcAuthentication()
                .dataSource(this.customUserDetailsService);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new
    }

    @Bean
    public SecurityFilterChain filter (HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/televisions").permitAll()
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .sessionManagement(sesh -> sesh.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(
                        new JwtRequestFilter(jwtUtil, userDetailsService()),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }
}