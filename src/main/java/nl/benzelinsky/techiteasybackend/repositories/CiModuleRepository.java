package nl.benzelinsky.techiteasybackend.repositories;

import nl.benzelinsky.techiteasybackend.models.CiModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiModuleRepository extends JpaRepository<CiModule, Long> {
}
