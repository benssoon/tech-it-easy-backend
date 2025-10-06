package nl.benzelinsky.techiteasybackend.mappers;

import nl.benzelinsky.techiteasybackend.dtos.SalesTelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionInputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.models.Television;

public class TelevisionMapper {

    public static Television toEntity(TelevisionInputDto tvInDto) {
        Television tv = new Television();

        tv.setType(tvInDto.type);
        tv.setBrand(tvInDto.brand);
        tv.setName(tvInDto.name);
        tv.setPrice(tvInDto.price);
        tv.setAvailableSize(tvInDto.availableSize);
        tv.setRefreshRate(tvInDto.refreshRate);
        tv.setScreenType(tvInDto.screenType);
        tv.setScreenQuality(tvInDto.screenQuality);
        tv.setSmartTv(tvInDto.smartTv);
        tv.setWifi(tvInDto.wifi);
        tv.setVoiceControl(tvInDto.voiceControl);
        tv.setHdr(tvInDto.hdr);
        tv.setBluetooth(tvInDto.bluetooth);
        tv.setAmbiLight(tvInDto.ambiLight);
        tv.setOriginalStock(tvInDto.originalStock);
        tv.setSold(tvInDto.sold);

        return tv;
    }

    public static TelevisionOutputDto toOutputDto(Television tv) {

        TelevisionOutputDto tvOutDto = new TelevisionOutputDto();

        tvOutDto.id = tv.getId();
        tvOutDto.type = tv.getType();
        tvOutDto.brand = tv.getBrand();
        tvOutDto.name = tv.getName();
        tvOutDto.price = tv.getPrice();
        tvOutDto.availableSize = tv.getAvailableSize();
        tvOutDto.refreshRate = tv.getRefreshRate();
        tvOutDto.screenType = tv.getScreenType();
        tvOutDto.screenQuality = tv.getScreenQuality();
        tvOutDto.smartTv = tv.getSmartTv();
        tvOutDto.wifi = tv.getWifi();
        tvOutDto.voiceControl = tv.getVoiceControl();
        tvOutDto.hdr = tv.getHdr();
        tvOutDto.bluetooth = tv.getBluetooth();
        tvOutDto.ambiLight = tv.getAmbiLight();
        tvOutDto.originalStock = tv.getOriginalStock();
        tvOutDto.sold = tv.getSold();

        return tvOutDto;
    }

    public static SalesTelevisionOutputDto toSalesDto(Television tv) {
        SalesTelevisionOutputDto salesDto = new SalesTelevisionOutputDto();

        salesDto.id = tv.getId();
        salesDto.price = tv.getPrice();
        salesDto.originalStock = tv.getOriginalStock();
        salesDto.sold = tv.getSold();

        return salesDto;
    }
}
