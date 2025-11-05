package nl.benzelinsky.techiteasybackend.repositories;


import nl.benzelinsky.techiteasybackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
