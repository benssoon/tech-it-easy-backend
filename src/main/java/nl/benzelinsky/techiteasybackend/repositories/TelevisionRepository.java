package nl.benzelinsky.techiteasybackend.repositories;

import nl.benzelinsky.techiteasybackend.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Integer> {
}
