package nl.benzelinsky.techiteasybackend.dtos;

import jakarta.validation.constraints.*;

public class TelevisionInputDto {

    @NotBlank
    @Size(min = 5, max = 128)
    public String type;

    @NotBlank
    @Size(min = 3, max = 64)
    public String brand;

    @NotBlank
    @Size(min=3, max = 128)
    public String name;

    @NotNull
    @Positive
    public Double price;

    @DecimalMin(value = "40.0")
    @DecimalMax(value = "800.0")
    public Double availableSize;

    public int refreshRate;

    public String screenType;

    public String screenQuality;

    public Boolean smartTv;

    public Boolean wifi;

    public Boolean voiceControl;

    public Boolean hdr;

    public Boolean bluetooth;

    public Boolean ambiLight;

    public Integer originalStock;

    public Integer sold;
}
