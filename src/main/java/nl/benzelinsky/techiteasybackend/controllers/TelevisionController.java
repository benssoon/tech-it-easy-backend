package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionInputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService service;

    public TelevisionController(TelevisionService service) {
        this.service = service;
    }


    //POST request for 1 television
    @PostMapping
    public ResponseEntity<TelevisionOutputDto> createTelevision(@Valid @RequestBody TelevisionInputDto tvInDto) {
        TelevisionOutputDto tvOutDto = this.service.createTelevision(tvInDto);

        URI location = URI.create("/televisions/" + tvOutDto.id);
        // Give the Response a location, based on the id of the new Television and show the
        // Television in the body.

        return ResponseEntity.created(location).body(tvOutDto);
        //return new ResponseEntity<>(tvOutDto, HttpStatus.CREATED);
    }

/*    //GET request all tvs
    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        return ResponseEntity.ok(this.repo.findAll());
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        Optional<Television> television = this.repo.findById(id);
        if (television.isPresent()) {
            return ResponseEntity.ok(television.get());
        } else { //Exception with message
            throw new RecordNotFoundException("ID cannot be found");
            //return ResponseEntity.notFound().build();
        }
    }

    //PUT request 1 tv
    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television newTelevision) {
        Optional<Television> tv = this.repo.findById(id);
        Television television = tv.get();

        //region Change values
        *//*Change only the values that are not null
        (i.e. whichever ones the client decides to change).*//*
        if (newTelevision.getType() != null) {
            television.setType(newTelevision.getType());
        }
        if (newTelevision.getBrand() != null) {
            television.setBrand(newTelevision.getBrand());
        }
        if (newTelevision.getName() != null) {
            television.setName(newTelevision.getName());
        }
        if (newTelevision.getPrice() != null) {
            television.setPrice(newTelevision.getPrice());
        }
        if (newTelevision.getAvailableSize() != null) {
            television.setAvailableSize(newTelevision.getAvailableSize());
        }
        if (newTelevision.getRefreshRate() != 0) {
            television.setRefreshRate(newTelevision.getRefreshRate());
        }
        if (newTelevision.getScreenType() != null) {
            television.setScreenType(newTelevision.getScreenType());
        }
        if (newTelevision.getScreenQuality() != null) {
            television.setScreenQuality(newTelevision.getScreenQuality());
        }
        if (newTelevision.getSmartTv() != null) {
            television.setSmartTv(newTelevision.getSmartTv());
        }
        if (newTelevision.getWifi() != null) {
            television.setWifi(newTelevision.getWifi());
        }
        if (newTelevision.getVoiceControl() != null) {
            television.setVoiceControl(newTelevision.getVoiceControl());
        }
        if (newTelevision.getHdr() != null) {
            television.setHdr(newTelevision.getHdr());
        }
        if (newTelevision.getBluetooth() != null) {
            television.setBluetooth(newTelevision.getBluetooth());
        }
        if (newTelevision.getAmbiLight() != null) {
            television.setAmbiLight(newTelevision.getAmbiLight());
        }
        if (newTelevision.getOriginalStock() != null) {
            television.setOriginalStock(newTelevision.getOriginalStock());
        }
        if (newTelevision.getSold() != null) {
            television.setSold(newTelevision.getSold());
        }
        //endregion

        return ResponseEntity.ok(this.repo.save(television));
    }

    //DELETE request 1 tv
    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {

        this.repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }*/
}