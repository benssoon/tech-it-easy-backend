package nl.benzelinsky.techiteasybackend.services;

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

    private final TelevisionRepository repo;

    public TelevisionService(TelevisionRepository repo) {
        this.repo = repo;
    }

    // Create a new television
    public TelevisionOutputDto createTelevision(TelevisionInputDto tvInDto) {
        Television tv = TelevisionMapper.toEntity(tvInDto);
        this.repo.save(tv);

        return TelevisionMapper.toOutputDto(tv);
    }

    // Get 1 television
    public TelevisionOutputDto getTelevisionById(int id) {
        return TelevisionMapper.toOutputDto(
                this.repo.findById(id)
                        .orElseThrow(() ->
                                new RecordNotFoundException("Television not found.")));
    }

    // Get all televisions
    public List<TelevisionOutputDto> getAllTelevisions() {
        List<TelevisionOutputDto> allTelevisions = new ArrayList<>();
        this.repo.findAll()
                .forEach((Television television) ->
                        allTelevisions.add(TelevisionMapper.toOutputDto(television)));
        return allTelevisions;
    }

    // Delete 1 television
    public String deleteTelevisionById(int id) {
        TelevisionOutputDto deletedTv = TelevisionMapper.toOutputDto(this.repo.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Television not found.")));
        this.repo.deleteById(id);
        return deletedTv.name + " deleted.";
    }

    // Update 1 television
    public TelevisionOutputDto updateTelevision(int id, TelevisionInputDto tvInDto) {
        Television newTelevision = TelevisionMapper.toEntity(tvInDto);
        Television toUpdateTelevision = this.repo.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Television not found."));

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

        this.repo.save(toUpdateTelevision);
        return TelevisionMapper.toOutputDto(toUpdateTelevision);
    }
}
