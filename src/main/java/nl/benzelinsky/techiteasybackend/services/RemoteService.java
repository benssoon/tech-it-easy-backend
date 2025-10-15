package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.RemoteInputDto;
import nl.benzelinsky.techiteasybackend.dtos.RemoteOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.mappers.RemoteMapper;
import nl.benzelinsky.techiteasybackend.models.Remote;
import nl.benzelinsky.techiteasybackend.repositories.RemoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteService {
    private final RemoteRepository repository;

    public RemoteService(RemoteRepository repository) {
        this.repository = repository;
    }

    // Create Remote
    public RemoteOutputDto createRemote(RemoteInputDto dtoIn) {
        Remote remote = RemoteMapper.toEntity(dtoIn);
        this.repository.save(remote);
        return RemoteMapper.toOutputDto(remote);
    }

    // Get Remote by ID
    public RemoteOutputDto getRemoteById(Long id) {
        return RemoteMapper.toOutputDto(
                this.repository.findById(id).orElseThrow(() ->
                        new RecordNotFoundException("Remote not found.")
                ));
    }

    // Get all Remotes
    public List<RemoteOutputDto> getAllRemotes() {
        List<RemoteOutputDto> allRemotes = new ArrayList<>();
        this.repository.findAll()
                .forEach(remote ->
                        allRemotes.add(RemoteMapper.toOutputDto(remote)));
        return allRemotes;
    }

    // Update 1 Remote by ID
    public RemoteOutputDto updateRemote(Long id, RemoteInputDto dtoIn) {
        Remote toUpdate = this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Remote not found."));
        toUpdate.setCompatibleWith(dtoIn.compatibleWith);
        toUpdate.setBatteryType(dtoIn.batteryType);
        toUpdate.setName(dtoIn.name);
        toUpdate.setBrand(dtoIn.brand);
        toUpdate.setPrice(dtoIn.price);
        toUpdate.setOriginalStock(dtoIn.originalStock);
        this.repository.save(toUpdate);
        return RemoteMapper.toOutputDto(toUpdate);
    }

    // Delete Remote by ID
    public String deleteRemoteById(Long id) {
        Remote toDelete = this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Remote not found."));
        this.repository.deleteById(id);
        return toDelete.getName() + " deleted.";
    }
}
