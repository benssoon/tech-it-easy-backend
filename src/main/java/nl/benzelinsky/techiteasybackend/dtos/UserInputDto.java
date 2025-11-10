package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserInputDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
    public Boolean enabled;
    public String apiKey;
    @NotBlank
    public String email; // Regex toevoegen


}
