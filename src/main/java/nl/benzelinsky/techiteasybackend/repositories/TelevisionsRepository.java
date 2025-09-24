package nl.benzelinsky.techiteasybackend.repositories;

import nl.benzelinsky.techiteasybackend.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionsRepository extends JpaRepository<Television, Integer> {
}
