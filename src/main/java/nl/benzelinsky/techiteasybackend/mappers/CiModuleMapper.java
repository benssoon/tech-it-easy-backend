package nl.benzelinsky.techiteasybackend.mappers;

import nl.benzelinsky.techiteasybackend.dtos.CiModuleInputDto;
import nl.benzelinsky.techiteasybackend.dtos.CiModuleOutputDto;
import nl.benzelinsky.techiteasybackend.models.CiModule;

public class CiModuleMapper {
    public static CiModule toEntity(CiModuleInputDto dtoIn) {
        CiModule ciModule = new CiModule();
        ciModule.setName(dtoIn.name);
        ciModule.setType(dtoIn.type);
        ciModule.setPrice(dtoIn.price);
        return ciModule;
    }

    public static CiModuleOutputDto toOutputDto(CiModule ciModule) {
        CiModuleOutputDto dtoOut = new CiModuleOutputDto();
        dtoOut.id = ciModule.getId();
        dtoOut.name = ciModule.getName();
        dtoOut.type = ciModule.getType();
        dtoOut.price = ciModule.getPrice();
        return dtoOut;
    }
}
