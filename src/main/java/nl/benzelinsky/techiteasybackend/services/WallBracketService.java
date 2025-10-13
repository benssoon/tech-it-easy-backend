package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.WallBracketInputDto;
import nl.benzelinsky.techiteasybackend.dtos.WallBracketOutputDto;
import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.mappers.WallBracketMapper;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.models.WallBracket;
import nl.benzelinsky.techiteasybackend.repositories.WallBracketRepository;

public class WallBracketService {

    private final WallBracketRepository repository;

    public WallBracketService(WallBracketRepository repository) {
        this.repository = repository;
    }

    // Create a new wall bracket
    public WallBracketOutputDto createWallBracket(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = WallBracketMapper.toEntity(wallBracketInputDto);
        this.repository.save(wallBracket);
        return WallBracketMapper.toOutputDto(wallBracket);
    }

    // Get 1 wall bracket
    public WallBracketOutputDto getWallBracketById(Long id) {
        return WallBracketMapper.toOutputDto(
                this.repository.findById(id)
                        .orElseThrow(() ->
                                new RecordNotFoundException("Wall Bracket not found.")));
    }

    // Update 1 wall bracket
    public WallBracketOutputDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        WallBracket newWallBracket = WallBracketMapper.toEntity(wallBracketInputDto);
        WallBracket toUpdateWallBracket = this.repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Wall Bracket not found."));

        toUpdateWallBracket.setName(newWallBracket.getName());
        toUpdateWallBracket.setSize(newWallBracket.getSize());
        toUpdateWallBracket.setPrice(newWallBracket.getPrice());
        toUpdateWallBracket.setAdjustable(newWallBracket.getAdjustable());

        this.repository.save(toUpdateWallBracket);
        return WallBracketMapper.toOutputDto(toUpdateWallBracket);
    }

    // Delete 1 wall bracket
    public String deleteWallBracketById(Long id) {
        WallBracketOutputDto deletedWallBracket = WallBracketMapper.toOutputDto(this.repository.findById(id)
                .orElseThrow(() ->
                        new RecordNotFoundException("Wall bracket not found.")));
        this.repository.deleteById(id);
        return deletedWallBracket.name + " deleted.";
    }

}
