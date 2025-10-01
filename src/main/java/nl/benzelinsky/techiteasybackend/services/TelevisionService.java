package nl.benzelinsky.techiteasybackend.services;

import nl.benzelinsky.techiteasybackend.dtos.TelevisionInputDto;
import nl.benzelinsky.techiteasybackend.dtos.TelevisionOutputDto;
import nl.benzelinsky.techiteasybackend.mappers.TelevisionMapper;
import nl.benzelinsky.techiteasybackend.models.Television;
import nl.benzelinsky.techiteasybackend.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

@Service
public class TelevisionService {

    private final TelevisionRepository repo;

    public TelevisionService(TelevisionRepository repo) {
        this.repo = repo;
    }

    public TelevisionOutputDto createTelevision(TelevisionInputDto tvInDto) {
        Television tv = TelevisionMapper.toEntity(tvInDto);
        this.repo.save(tv);

        return TelevisionMapper.toOutputDto(tv);
    }

    /*public TelevisionOutputDto getTelevisionById() {

    }*/
}
