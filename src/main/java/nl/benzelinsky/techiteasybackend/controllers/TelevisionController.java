package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.IdInputDto;
import nl.benzelinsky.techiteasybackend.dtos.SalesTelevisionOutputDto;
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
        TelevisionOutputDto dtoOut = this.service.createTelevision(tvInDto);

        URI location = URI.create("/televisions/" + dtoOut.id);
        // Give the Response a location, based on the id of the new Television.
        // Location is found in the header. Show the Television in the body.

        return ResponseEntity.created(location).body(dtoOut);
        //return new ResponseEntity<>(dtoOut, HttpStatus.CREATED);
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> getTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getTelevisionById(id));
    }

    //GET request all tvs
    @GetMapping
    public ResponseEntity<List<TelevisionOutputDto>> getAllTelevisions() {
        return ResponseEntity.ok(this.service.getAllTelevisions());
    }

    //PUT request 1 tv
    @PutMapping("/{id}")
    public ResponseEntity<TelevisionOutputDto> updateTelevision(@PathVariable Long id,
                                                                @Valid @RequestBody TelevisionInputDto dtoIn) {
        return ResponseEntity.ok(this.service.updateTelevision(id, dtoIn));
    }

    // Couple Television with Remote
    @PutMapping("/{televisionId}/remote")
    public ResponseEntity<TelevisionOutputDto> assignRemoteToTelevision(@PathVariable Long televisionId,
                                                                        @Valid @RequestBody IdInputDto remoteIdDto) {
        return ResponseEntity.ok(this.service.assignRemoteToTelevision(televisionId, remoteIdDto.id));
    }

    //DELETE request 1 tv
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.deleteTelevisionById(id));
    }

    //GET only sales info from all tvs
    @GetMapping("/sales")
    public ResponseEntity<List<SalesTelevisionOutputDto>> getAllTelevisionsSalesInfo() {
        List<SalesTelevisionOutputDto> allTelevisionsSalesInfo = this.service.getAllTelevisionsSalesInfo();
        return ResponseEntity.ok(allTelevisionsSalesInfo);
    }
}