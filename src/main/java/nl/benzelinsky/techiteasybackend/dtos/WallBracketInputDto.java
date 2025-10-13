package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.NotBlank;

public class WallBracketInputDto {

    public String size;
    public Boolean isAdjustable;
    @NotBlank
    public String name;
    @NotBlank
    public Double price;
}
