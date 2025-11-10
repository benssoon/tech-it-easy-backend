package nl.benzelinsky.techiteasybackend.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.benzelinsky.techiteasybackend.models.Authority;

import java.util.Set;

public class UserOutputDto {
    public String username;
    public String password;
    public Boolean enabled;
    public String apiKey;
    public String email;
    @JsonSerialize
    public Set<Authority> authorities;
}
