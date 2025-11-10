package nl.benzelinsky.techiteasybackend.mappers;

import nl.benzelinsky.techiteasybackend.dtos.UserInputDto;
import nl.benzelinsky.techiteasybackend.dtos.UserOutputDto;
import nl.benzelinsky.techiteasybackend.models.User;
import nl.benzelinsky.techiteasybackend.utils.RandomStringGenerator;

public class UserMapper {

    public static User toEntity(UserInputDto userInDto) {

        User user = new User();

        user.setUsername(userInDto.username);
        user.setPassword(RandomStringGenerator.generateAlphaNumeric(16));
        user.setEnabled(userInDto.enabled);
        user.setApikey(userInDto.apiKey);
        user.setEmail(userInDto.email);

        return user;
    }

    public static UserOutputDto toOutputDto(User user){

        UserOutputDto dtoOut = new UserOutputDto();

        dtoOut.username = user.getUsername();
        dtoOut.password = user.getPassword();
        dtoOut.enabled = user.isEnabled();
        dtoOut.apiKey = user.getApikey();
        dtoOut.email = user.getEmail();
        dtoOut.authorities = user.getAuthorities();

        return dtoOut;
    }
}
