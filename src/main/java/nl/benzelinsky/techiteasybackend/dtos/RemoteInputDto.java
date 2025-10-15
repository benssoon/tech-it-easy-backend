package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.*;

public class RemoteInputDto {
    @NotBlank
    @Size(min = 3, max = 128)
    public String compatibleWith;

    @Size(min = 2, max = 64)
    public String batteryType;

    @NotBlank
    @Size(min = 3, max = 128)
    public String name;

    @Size(min = 2, max = 64)
    public String brand;

    @NotNull
    @Positive
    public Double price;

    @NotNull
    @PositiveOrZero
    public Integer originalStock;
}
