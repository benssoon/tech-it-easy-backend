package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.SalesTelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionInputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.mappers.TelevisionMapper;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository repository;

    public TelevisionService(TelevisionRepository repo) {
        this.repository = repo;
    }

    // Create a new television
    public TelevisionOutputDto createTelevision(TelevisionInputDto tvInDto) {
        Television tv = TelevisionMapper.toEntity(tvInDto);
        this.repository.save(tv);

        return TelevisionMapper.toOutputDto(tv);
    }

    // Get 1 television
    public TelevisionOutputDto getTelevisionById(Long id) {
        return TelevisionMapper.toOutputDto(
                this.repository.findById(id)
                        .orElseThrow(() ->
                                new RecordNotFoundException("Television not found.")));
    }

    // Get all televisions
    public List<TelevisionOutputDto> getAllTelevisions() {
        List<TelevisionOutputDto> allTelevisions = new ArrayList<>();
        this.repository.findAll()
                .forEach(television ->
                        allTelevisions.add(TelevisionMapper.toOutputDto(television)));
        return allTelevisions;
    }

    // Update 1 television
    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto tvInDto) {
        Television toUpdate = this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Television not found."));


        //region Change values

        toUpdate.setType(tvInDto.type);
        toUpdate.setBrand(tvInDto.brand);
        toUpdate.setName(tvInDto.name);
        toUpdate.setPrice(tvInDto.price);
        toUpdate.setAvailableSize(tvInDto.availableSize);
        toUpdate.setRefreshRate(tvInDto.refreshRate);
        toUpdate.setScreenType(tvInDto.screenType);
        toUpdate.setScreenQuality(tvInDto.screenQuality);
        toUpdate.setSmartTv(tvInDto.smartTv);
        toUpdate.setWifi(tvInDto.wifi);
        toUpdate.setVoiceControl(tvInDto.voiceControl);
        toUpdate.setHdr(tvInDto.hdr);
        toUpdate.setBluetooth(tvInDto.bluetooth);
        toUpdate.setAmbiLight(tvInDto.ambiLight);
        toUpdate.setOriginalStock(tvInDto.originalStock);
        toUpdate.setSold(tvInDto.sold);
        //endregion

        this.repository.save(toUpdate);
        return TelevisionMapper.toOutputDto(toUpdate);
    }


/*
// This is more like a PATCH request!
        //region Change values

        // Change only the values that are not null
        // (i.e. whichever ones the client decides to change).

        if (newTelevision.getType() != null) {
            toUpdateTelevision.setType(newTelevision.getType());
        }
        if (newTelevision.getBrand() != null) {
            toUpdateTelevision.setBrand(newTelevision.getBrand());
        }
        if (newTelevision.getName() != null) {
            toUpdateTelevision.setName(newTelevision.getName());
        }
        if (newTelevision.getPrice() != null) {
            toUpdateTelevision.setPrice(newTelevision.getPrice());
        }
        if (newTelevision.getAvailableSize() != null) {
            toUpdateTelevision.setAvailableSize(newTelevision.getAvailableSize());
        }
        if (newTelevision.getRefreshRate() != 0) {
            toUpdateTelevision.setRefreshRate(newTelevision.getRefreshRate());
        }
        if (newTelevision.getScreenType() != null) {
            toUpdateTelevision.setScreenType(newTelevision.getScreenType());
        }
        if (newTelevision.getScreenQuality() != null) {
            toUpdateTelevision.setScreenQuality(newTelevision.getScreenQuality());
        }
        if (newTelevision.getSmartTv() != null) {
            toUpdateTelevision.setSmartTv(newTelevision.getSmartTv());
        }
        if (newTelevision.getWifi() != null) {
            toUpdateTelevision.setWifi(newTelevision.getWifi());
        }
        if (newTelevision.getVoiceControl() != null) {
            toUpdateTelevision.setVoiceControl(newTelevision.getVoiceControl());
        }
        if (newTelevision.getHdr() != null) {
            toUpdateTelevision.setHdr(newTelevision.getHdr());
        }
        if (newTelevision.getBluetooth() != null) {
            toUpdateTelevision.setBluetooth(newTelevision.getBluetooth());
        }
        if (newTelevision.getAmbiLight() != null) {
            toUpdateTelevision.setAmbiLight(newTelevision.getAmbiLight());
        }
        if (newTelevision.getOriginalStock() != null) {
            toUpdateTelevision.setOriginalStock(newTelevision.getOriginalStock());
        }
        if (newTelevision.getSold() != null) {
            toUpdateTelevision.setSold(newTelevision.getSold());
        }
        //endregion
*/


    // Delete 1 television
    public String deleteTelevisionById(Long id) {
        Television toDelete = this.repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Television not found."));
        this.repository.deleteById(id);
        return toDelete.getName() + " deleted.";
    }

    // Get sales info of all televisions
    public List<SalesTelevisionOutputDto> getAllTelevisionsSalesInfo() {
        List<SalesTelevisionOutputDto> allTelevisionsSalesInfo = new ArrayList<>();
        this.repository.findAll()
                .forEach((Television television) ->
                        allTelevisionsSalesInfo.add(TelevisionMapper.toSalesDto(television)));
        return allTelevisionsSalesInfo;
    }
}
