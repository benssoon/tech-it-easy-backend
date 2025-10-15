package nl.benzelinsky.techiteasybackend.repositories;

import nl.benzelinsky.techiteasybackend.models.Remote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteRepository extends JpaRepository<Remote, Long> {
}
