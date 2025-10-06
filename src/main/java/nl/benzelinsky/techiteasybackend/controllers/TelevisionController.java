package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionInputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
        // Give the Response a location, based on the id of the new Television.
        // Location is found in the header. Show the Television in the body.

        return ResponseEntity.created(location).body(tvOutDto);
        //return new ResponseEntity<>(tvOutDto, HttpStatus.CREATED);
    }

    //GET request all tvs
    @GetMapping
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisions() {
        List<TelevisionOutputDto> allTelevisions = this.service.getAllTelevisions();
        return ResponseEntity.ok(allTelevisions);
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> getTelevisionById(@PathVariable int id) {
        
        return ResponseEntity.ok(this.service.getTelevisionById(id));
    }

    //PUT request 1 tv
    @PutMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> updateTelevision(@PathVariable int id, @RequestBody TelevisionInputDto toUpdateTelevision) {

        return ResponseEntity.ok(this.service.updateTelevision(id, toUpdateTelevision));
    }


    //DELETE request 1 tv
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable int id) {

        return ResponseEntity.ok(this.service.deleteTelevisionById(id));
    }
}