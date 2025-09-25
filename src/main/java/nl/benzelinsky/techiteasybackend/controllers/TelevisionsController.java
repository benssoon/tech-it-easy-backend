package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.repositories.TelevisionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {

    private final TelevisionsRepository repo;

    public TelevisionsController(TelevisionsRepository repo) {
        this.repo = repo;
    }


    //POST request 1 tv
    @PostMapping
    public ResponseEntity<Object> createTelevision(@RequestBody Television television) {
        this.repo.save(television);

        return new ResponseEntity<>(television, HttpStatus.CREATED);
        //return ResponseEntity.created(null).body(television);
    }

    //GET request all tvs
    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        return ResponseEntity.ok(this.repo.findAll());
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        // Check if this needs to be Integer!!!
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
        
        television.setType(newTelevision.getType());
        television.setBrand(newTelevision.getBrand());
        television.setName(newTelevision.getName());
        television.setPrice(newTelevision.getPrice());
        television.setAvailableSize(newTelevision.getAvailableSize());
        television.setRefreshRate(newTelevision.getRefreshRate());
        television.setScreenType(newTelevision.getScreenType());
        television.setScreenQuality(newTelevision.getScreenQuality());
        television.setSmartTv(newTelevision.getSmartTv());
        television.setWifi(newTelevision.getWifi());
        television.setVoiceControl(newTelevision.getVoiceControl());
        television.setHdr(newTelevision.getHdr());
        television.setBluetooth(newTelevision.getBluetooth());
        television.setAmbiLight(newTelevision.getAmbiLight());
        television.setOriginalStock(newTelevision.getOriginalStock());
        television.setSold(newTelevision.getSold());

        return ResponseEntity.ok(this.repo.save(television));
    }

    //DELETE request 1 tv
    @DeleteMapping
    public ResponseEntity<String> deleteTelevison() {
        return ResponseEntity.ok("deleted television");
    }
}