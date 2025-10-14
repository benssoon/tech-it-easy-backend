package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.RemoteInputDto;
import nl.benzelinsky.techiteasybackend.dtos.RemoteOutputDto;
import nl.benzelinsky.techiteasybackend.services.RemoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remotes")
public class RemoteController {

    private final RemoteService service;

    public RemoteController(RemoteService service) {
        this.service = service;
    }

    // Create Remote
    @PostMapping
    public ResponseEntity<RemoteOutputDto> createRemote(@Valid @RequestBody RemoteInputDto dtoIn) {
        RemoteOutputDto dtoOut = this.service.createRemote(dtoIn);
        URI location = URI.create("/remotes/" + dtoOut.id);
        return ResponseEntity.created(location).body(dtoOut);
    }

    // Get Remote by ID
    @GetMapping("/{id}")
    public ResponseEntity<RemoteOutputDto> getRemoteById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getRemoteById(id));
    }

    // Get all Remotes
    @GetMapping
    public ResponseEntity<List<RemoteOutputDto>> getAllRemotes() {
        return ResponseEntity.ok(this.service.getAllRemotes());
    }

    // Update 1 Remote by ID
    @PutMapping("/{id}")
    public ResponseEntity<RemoteOutputDto> updateRemote(@PathVariable Long id,
                                                        @Valid @RequestBody RemoteInputDto dtoIn) {
        return ResponseEntity.ok(this.service.updateRemote(id, dtoIn));
    }

    // Delete Remote by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRemote(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.deleteRemoteById(id));
    }
}
