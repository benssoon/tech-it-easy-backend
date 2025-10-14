package nl.benzelinsky.techiteasybackend.mappers;

import nl.benzelinsky.techiteasybackend.dtos.RemoteInputDto;
import nl.benzelinsky.techiteasybackend.dtos.RemoteOutputDto;
import nl.benzelinsky.techiteasybackend.models.Remote;

public class RemoteMapper {

    public static Remote toEntity(RemoteInputDto dtoIn) {
        Remote remote = new Remote();
        remote.setCompatibleWith(dtoIn.compatibleWith);
        remote.setBatteryType(dtoIn.batteryType);
        remote.setName(dtoIn.name);
        remote.setBrand(dtoIn.brand);
        remote.setPrice(dtoIn.price);
        remote.setOriginalStock(dtoIn.originalStock);
        return remote;
    }

    public static RemoteOutputDto toOutputDto(Remote remote) {
        RemoteOutputDto dtoOut = new RemoteOutputDto();
        dtoOut.id = remote.getId();
        dtoOut.compatibleWith = remote.getCompatibleWith();
        dtoOut.batteryType = remote.getBatteryType();
        dtoOut.name = remote.getName();
        dtoOut.brand = remote.getBrand();
        dtoOut.price = remote.getPrice();
        dtoOut.originalStock = remote.getOriginalStock();
        return dtoOut;
    }

}
