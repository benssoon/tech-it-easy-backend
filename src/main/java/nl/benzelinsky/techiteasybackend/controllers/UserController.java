package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.dtos.UserInputDto;
import nl.benzelinsky.techiteasybackend.dtos.UserOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.BadRequestException;
import nl.benzelinsky.techiteasybackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService us) {
        this.userService = us;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserOutputDto>> getUsers() {

        List<UserOutputDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {

        UserOutputDto optionalUser = userService.getUser(username);


        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping(value = "")
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto dtoIn) {;

        String newUsername = userService.createUser(dtoIn);
        userService.addRole(newUsername, "ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable("username") String username, @RequestBody UserInputDto dtoIn) {

        userService.updateUser(username, dtoIn);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/roles")
    public ResponseEntity<Object> getUserRoles(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getRoles(username));
    }

    //TODO: Als Requestbody wordt hier een Map<String, Object> gebruikt om de "roleName" binnen te halen, dat werkt, maar kun je een beter oplossing bedenken?
    @PostMapping(value = "/{username}/roles")
    public ResponseEntity<Object> addUserRole(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String roleName = (String) fields.get("role");
            userService.addRole(username, roleName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/roles/{role}")
    public ResponseEntity<Object> deleteUserRole(@PathVariable("username") String username, @PathVariable("role") String role) {
        userService.removeRole(username, role);
        return ResponseEntity.noContent().build();
    }

}
