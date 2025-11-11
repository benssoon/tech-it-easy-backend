package nl.benzelinsky.techiteasybackend.security;


import nl.benzelinsky.techiteasybackend.filter.JwtRequestFilter;
import nl.benzelinsky.techiteasybackend.repositories.UserRepository;
import nl.benzelinsky.techiteasybackend.services.CustomUserDetailsService;
import nl.benzelinsky.techiteasybackend.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

private final JwtUtil jwtUtil;
private final UserRepository userRepository;
private final CustomUserDetailsService customUserDetailsService;
private final UserDetailsService userDetailsService;

public SpringSecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, CustomUserDetailsService customUd, UserDetailsService ud) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepo;
    this.customUserDetailsService = customUd;
    this.userDetailsService = ud;
}
    */
/*@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService);
        return new ProviderManager(auth);
    }*//*

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(this.us);
    }

    @Bean
    public SecurityFilterChain filter (HttpSecurity http) throws Exception {
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
}*/

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /*TODO inject customUserDetailService en jwtRequestFilter*/
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService cud, JwtRequestFilter jwtFilter) {
        this.customUserDetailsService = cud;
        this.jwtRequestFilter = jwtFilter;
    }


    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }



    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
                                        //.requestMatchers("/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/televisions").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/televisions").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/remotes").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/remotes").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/ci-modules").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/ci-modules").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/wall-brackets").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/wall-brackets").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/**").hasRole("USER")
                                        /*TODO voeg de antmatchers toe voor admin(post en delete) en user (overige)*/
                                        .requestMatchers("/authenticated").authenticated()
                                        .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                                        .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}