package nl.benzelinsky.techiteasybackend.mappers;

import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.dtos.WallBracketInputDto;
import nl.benzelinsky.techiteasybackend.dtos.WallBracketOutputDto;
import nl.benzelinsky.techiteasybackend.models.WallBracket;

public class WallBracketMapper {

    public static WallBracket toEntity(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = new WallBracket();
        wallBracket.setSize(wallBracketInputDto.size);
        wallBracket.setAdjustable(wallBracketInputDto.isAdjustable);
        wallBracket.setName(wallBracketInputDto.name);
        wallBracket.setPrice(wallBracketInputDto.price);

        return wallBracket;
    }

    public static WallBracketOutputDto toOutputDto(WallBracket wallBracket) {
        WallBracketOutputDto wallBracketOutputDto = new WallBracketOutputDto();
        wallBracketOutputDto.id = wallBracket.getId();
        wallBracketOutputDto.size = wallBracket.getSize();
        wallBracketOutputDto.isAdjustable = wallBracket.getAdjustable();
        wallBracketOutputDto.name = wallBracket.getName();
        wallBracketOutputDto.price = wallBracket.getPrice();

        return wallBracketOutputDto;
    }


}
