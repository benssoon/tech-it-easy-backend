package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.UserInputDto;
import nl.benzelinsky.techiteasybackend.dtos.UserOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.UsernameNotFoundException;
import nl.benzelinsky.techiteasybackend.mappers.UserMapper;
import nl.benzelinsky.techiteasybackend.models.Role;
import nl.benzelinsky.techiteasybackend.models.User;
import nl.benzelinsky.techiteasybackend.repositories.UserRepository;
import nl.benzelinsky.techiteasybackend.utils.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserOutputDto> getUsers() {
        List<UserOutputDto> collection = new ArrayList<>();
        List<User> list = this.userRepository.findAll();
        for (User user : list) {
            collection.add(UserMapper.toOutputDto(user));
        }
        return collection;
    }

    public UserOutputDto getUser(String username) {
        UserOutputDto dto = new UserOutputDto();
        Optional<User> user = this.userRepository.findById(username);
        if (user.isPresent()){
            dto = UserMapper.toOutputDto(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return this.userRepository.existsById(username);
    }

    public String createUser(UserInputDto userInputDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userInputDto.apiKey = randomString;
        userInputDto.password = passwordEncoder.encode(userInputDto.password);
        User newUser = this.userRepository.save(UserMapper.toEntity(userInputDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        this.userRepository.deleteById(username);
    }

    public void updateUser(String username, UserInputDto newUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = this.userRepository.findById(username).get();
        user.setPassword(newUser.password);
        this.userRepository.save(user);
    }

    public Set<Role> getRoles(String username) {
        if (!this.userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = this.userRepository.findById(username).get();
        UserOutputDto userDto = UserMapper.toOutputDto(user);
        return userDto.roles;
    }

    public void addRole(String username, String role) {

        if (!this.userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = this.userRepository.findById(username).get();
        user.addRole(new Role(username, role));
        this.userRepository.save(user);
    }

    public void removeRole(String username, String role) {
        if (!this.userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = this.userRepository.findById(username).get();
        Role roleToRemove = user.getRoles().stream().filter((a) -> a.getRole().equalsIgnoreCase(role)).findAny().get();
        user.removeRole(roleToRemove);
        this.userRepository.save(user);
    }
}
