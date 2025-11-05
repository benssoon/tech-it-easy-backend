package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.models.User;
import nl.benzelinsky.techiteasybackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomerDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerDetailsService(UserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);
    }
}
