package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class IdInputDto {
    @NotNull
    @Positive
    public Long id;
}
