package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.dtos.AuthenticationRequest;
import nl.benzelinsky.techiteasybackend.dtos.AuthenticationResponse;
import nl.benzelinsky.techiteasybackend.services.CustomUserDetailsService;
import nl.benzelinsky.techiteasybackend.services.UserService;
import nl.benzelinsky.techiteasybackend.utils.JwtUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {

    /*TODO inject authentionManager, userDetailService en jwtUtil*/
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authMan, JwtUtil jwtUtil, CustomUserDetailsService ud) {
        this.authenticationManager = authMan;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = ud;
    }

    /*
         Deze methode geeft de principal (basis user gegevens) terug van de ingelogde gebruiker
     */
    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

    /*
    Deze methode geeft het JWT token terug wanneer de gebruiker de juiste inloggegevens op geeft.
     */
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}