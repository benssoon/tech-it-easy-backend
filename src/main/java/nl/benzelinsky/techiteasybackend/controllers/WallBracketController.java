package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.WallBracketInputDto;
import nl.benzelinsky.techiteasybackend.dtos.WallBracketOutputDto;
import nl.benzelinsky.techiteasybackend.repositories.WallBracketRepository;
import nl.benzelinsky.techiteasybackend.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/wall-brackets")
public class WallBracketController {

    private final WallBracketService service;

    public WallBracketController(WallBracketService service) {
        this.service = service;
    }

    //POST request for 1 television
    @PostMapping
    public ResponseEntity<WallBracketOutputDto> createTelevision(@Valid @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketOutputDto wallBracketOutputDto = this.service.createWallBracket(wallBracketInputDto);

        URI location = URI.create("/wall-brackets/" + wallBracketOutputDto.id);
        // Give the Response a location, based on the id of the new Television.
        // Location is found in the header. Show the Television in the body.

        return ResponseEntity.created(location).body(wallBracketOutputDto);
        //return new ResponseEntity<>(tvOutDto, HttpStatus.CREATED);
    }

}
