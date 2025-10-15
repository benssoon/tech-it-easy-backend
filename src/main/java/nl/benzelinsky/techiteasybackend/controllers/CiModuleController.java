package nl.benzelinsky.techiteasybackend.controllers;

import jakarta.validation.Valid;
import nl.benzelinsky.techiteasybackend.dtos.CiModuleInputDto;
import nl.benzelinsky.techiteasybackend.dtos.CiModuleOutputDto;
import nl.benzelinsky.techiteasybackend.services.CiModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ci-modules")
public class CiModuleController {

    private final CiModuleService service;

    public CiModuleController(CiModuleService service) {
        this.service = service;
    }

    // Create CI Module
    @PostMapping
    public ResponseEntity<CiModuleOutputDto> createCiModule(@Valid @RequestBody CiModuleInputDto dtoIn) {
        CiModuleOutputDto dtoOut = this.service.createCiModule(dtoIn);
        URI location = URI.create("/ci-modules/" + dtoOut.id);
        return ResponseEntity.created(location).body(dtoOut);
    }

    // Get CI Module by ID
    @GetMapping("/{id}")
    public ResponseEntity<CiModuleOutputDto> getCiModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getCiModuleById(id));
    }

    // Get all CI Modules
    @GetMapping
    public ResponseEntity<List<CiModuleOutputDto>> getAllCiModules() {
        return ResponseEntity.ok(this.service.getAllCiModules());
    }

    // Update 1 CI Module by ID
    @PutMapping("/{id}")
    public ResponseEntity<CiModuleOutputDto> updateCiModule(@PathVariable Long id,
                                                            @Valid @RequestBody CiModuleInputDto dtoIn) {
        return ResponseEntity.ok(this.service.updateCiModule(id, dtoIn));
    }

    // Delete CI Module by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCiModule(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.deleteCiModuleById(id));
    }
}
