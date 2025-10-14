package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.*;

public class WallBracketInputDto {

    public String size;
    public Boolean isAdjustable;
    @NotBlank
    @Size(min = 3, max = 128)
    public String name;
    @NotBlank
    @Positive
    public Double price;
}
