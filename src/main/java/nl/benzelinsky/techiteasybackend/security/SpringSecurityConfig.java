package nl.benzelinsky.techiteasybackend.security;

import nl.benzelinsky.techiteasybackend.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig {

private final JwtUtil jwtUtil;
private final UserRepository userRepository;
private final DataSource dataSource;

public SpringSecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, DataSource dataSource) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepo;
    this.dataSource = dataSource;
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
                .dataSource(dataSource);

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