package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.*;

public class CiModuleInputDto {
    @NotBlank
    @Size(min = 3, max = 128)
    public String name;

    @NotBlank
    @Size(min = 3, max = 128)
    public String type;

    @NotNull
    @Positive
    public Double price;
}
