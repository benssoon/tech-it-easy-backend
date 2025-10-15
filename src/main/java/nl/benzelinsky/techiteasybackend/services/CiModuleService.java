package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.CiModuleInputDto;
import nl.benzelinsky.techiteasybackend.dtos.CiModuleOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.mappers.CiModuleMapper;
import nl.benzelinsky.techiteasybackend.models.CiModule;
import nl.benzelinsky.techiteasybackend.repositories.CiModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiModuleService {

    private final CiModuleRepository repository;

    public CiModuleService(CiModuleRepository repository) {
        this.repository = repository;
    }

    // Create CI Module
    public CiModuleOutputDto createCiModule(CiModuleInputDto dtoIn) {
        CiModule ciModule = CiModuleMapper.toEntity(dtoIn);
        this.repository.save(ciModule);
        return CiModuleMapper.toOutputDto(ciModule);
    }

    // Get CI Module by ID
    public CiModuleOutputDto getCiModuleById(Long id) {
        return CiModuleMapper.toOutputDto(
                this.repository.findById(id).orElseThrow(() ->
                        new RecordNotFoundException("CI Module not found.")
                ));
    }

    // Get all CI Modules
    public List<CiModuleOutputDto> getAllCiModules() {
        List<CiModuleOutputDto> allCiModules = new ArrayList<>();
        this.repository.findAll()
                .forEach(ciModule ->
                        allCiModules.add(CiModuleMapper.toOutputDto(ciModule)));
        return allCiModules;
    }

    // Update 1 CI Module by ID
    public CiModuleOutputDto updateCiModule(Long id, CiModuleInputDto dtoIn) {
        CiModule toUpdate = this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("CI Module not found."));
        toUpdate.setName(dtoIn.name);
        toUpdate.setType(dtoIn.type);
        toUpdate.setPrice(dtoIn.price);
        this.repository.save(toUpdate);
        return CiModuleMapper.toOutputDto(toUpdate);
    }

    // Delete CI Module by ID
    public String deleteCiModuleById(Long id) {
        CiModule toDelete = this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("CI Module not found."));
        this.repository.deleteById(id);
        return toDelete.getName() + " deleted.";
    }

}
